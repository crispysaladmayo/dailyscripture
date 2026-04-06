package com.catholicdaily.app.data.repository

import androidx.room.withTransaction
import com.catholicdaily.app.data.homily.HomilyDataSource
import com.catholicdaily.app.data.local.CatholicDatabase
import com.catholicdaily.app.data.model.HomeDailyContent
import com.catholicdaily.app.data.model.ReadingsDayContent
import com.catholicdaily.app.data.remote.EditorialPieceDto
import com.catholicdaily.app.data.remote.toEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class DefaultContentRepository(
    private val database: CatholicDatabase,
    private val okHttpClient: OkHttpClient,
    editorialBaseUrl: String,
    private val homilyDataSource: HomilyDataSource,
) : ContentRepository {

    private val editorialBaseUrl = editorialBaseUrl.trimEnd('/')
    private val moshi: Moshi =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    private val editorialAdapter = moshi.adapter(EditorialPieceDto::class.java)

    private val readingsDao = database.readingsDao()
    private val homilyDao = database.homilyDao()
    private val editorialDao = database.editorialDao()

    override fun observeHome(liturgicalDate: String): Flow<HomeDailyContent> {
        val readingsFlow =
            readingsDao.observeLatestBundle().flatMapLatest { bundle ->
                flow {
                    if (bundle == null) {
                        emit(null)
                        return@flow
                    }
                    val day = readingsDao.getDayForBundle(liturgicalDate, bundle.id)
                    if (day == null) {
                        emit(null)
                        return@flow
                    }
                    val blocks = readingsDao.blocksForDay(day.id)
                    emit(
                        ReadingsDayContent(
                            liturgicalDate = liturgicalDate,
                            bundleVersion = bundle.bundleVersion,
                            sourceAttribution = bundle.sourceAttribution,
                            blocks = blocks,
                        ),
                    )
                }
            }
        return combine(
            readingsFlow,
            homilyDao.observeLatestHomily(),
            editorialDao.observeEditorial(liturgicalDate),
        ) { readings, homily, editorial ->
            HomeDailyContent(
                liturgicalDate = liturgicalDate,
                readings = readings,
                homily = homily,
                editorial = editorial,
            )
        }
    }

    override suspend fun refreshHomilyIfNeeded() =
        withContext(Dispatchers.IO) {
            val entity = homilyDataSource.fetchLatest() ?: return@withContext
            database.withTransaction {
                homilyDao.clearLatestFlags()
                homilyDao.insertHomily(entity.copy(isLatest = true))
            }
        }

    override suspend fun refreshEditorial(liturgicalDate: String) =
        withContext(Dispatchers.IO) {
            if (editorialBaseUrl.isEmpty() ||
                editorialBaseUrl.contains("example.invalid", ignoreCase = true)
            ) {
                return@withContext
            }
            val url = "$editorialBaseUrl/editorial/$liturgicalDate.json"
            val request = Request.Builder().url(url).build()
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use
                val body = response.body?.string() ?: return@use
                val dto = editorialAdapter.fromJson(body) ?: return@use
                editorialDao.insert(dto.toEntity())
            }
        }
}
