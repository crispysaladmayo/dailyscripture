package com.catholicdaily.app.data.importer

import android.content.Context
import com.catholicdaily.app.data.local.CatholicDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Copies [ASSET_NAME] from app assets (project root of assets/) into a temp file, then ATTACH-imports
 * readings tables into the Room database. The asset must be a SQLite file produced by
 * `pipeline/scripts/build_readings_bundle.py` with the same schema as [sql/readings_tables.sql].
 */
class ReadingsBundleImporter(
    private val database: CatholicDatabase,
) {
    suspend fun importFromAssetsIfPresent(context: Context): Unit =
        withContext(Dispatchers.IO) {
            val root = context.assets.list("") ?: return@withContext
            if (ASSET_NAME !in root) return@withContext

            val tmp = File(context.cacheDir, "readings_bundle_import.db")
            context.assets.open(ASSET_NAME).use { input ->
                tmp.outputStream().use { output -> input.copyTo(output) }
            }

            val path = sqlQuotePath(tmp.absolutePath)
            val db = database.openHelper.writableDatabase
            db.beginTransaction()
            try {
                db.execSQL("DELETE FROM reading_block")
                db.execSQL("DELETE FROM readings_day")
                db.execSQL("DELETE FROM readings_bundle")
                db.execSQL("ATTACH DATABASE '$path' AS src")
                db.execSQL("INSERT INTO readings_bundle SELECT * FROM src.readings_bundle")
                db.execSQL("INSERT INTO readings_day SELECT * FROM src.readings_day")
                db.execSQL("INSERT INTO reading_block SELECT * FROM src.reading_block")
                db.execSQL("DETACH DATABASE src")
                db.setTransactionSuccessful()
            } catch (t: Throwable) {
                try {
                    db.execSQL("DETACH DATABASE src")
                } catch (_: Exception) {
                    // ignore
                }
                throw t
            } finally {
                db.endTransaction()
                tmp.delete()
            }
        }

    private fun sqlQuotePath(path: String): String = path.replace("'", "''")

    companion object {
        const val ASSET_NAME = "readings_bundle.db"
    }
}
