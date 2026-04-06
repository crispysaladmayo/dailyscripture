package com.catholicdaily.app

import android.app.Application
import com.catholicdaily.app.data.calendar.DefaultLiturgicalDateResolver
import com.catholicdaily.app.data.calendar.LiturgicalDateResolver
import com.catholicdaily.app.data.homily.JsonHomilyFeedDataSource
import com.catholicdaily.app.data.homily.NoOpHomilyDataSource
import com.catholicdaily.app.data.importer.ReadingsBundleImporter
import com.catholicdaily.app.data.local.CatholicDatabase
import com.catholicdaily.app.data.repository.ContentRepository
import com.catholicdaily.app.data.repository.DefaultContentRepository
import com.catholicdaily.app.work.ContentRefreshScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class CatholicDailyApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    lateinit var database: CatholicDatabase
        private set

    lateinit var contentRepository: ContentRepository
        private set

    val liturgicalDateResolver: LiturgicalDateResolver = DefaultLiturgicalDateResolver()

    override fun onCreate() {
        super.onCreate()
        database = CatholicDatabase.create(this)
        applicationScope.launch {
            ReadingsBundleImporter(database).importFromAssetsIfPresent(this@CatholicDailyApplication)
        }

        val okHttp = OkHttpClient()
        val homilyUrl = BuildConfig.HOMILY_FEED_URL.trim()
        val homilySource =
            if (homilyUrl.isNotEmpty()) {
                JsonHomilyFeedDataSource(okHttp, homilyUrl)
            } else {
                NoOpHomilyDataSource
            }

        contentRepository =
            DefaultContentRepository(
                database = database,
                okHttpClient = okHttp,
                editorialBaseUrl = BuildConfig.EDITORIAL_BASE_URL,
                homilyDataSource = homilySource,
            )

        ContentRefreshScheduler.schedule(this)
    }
}
