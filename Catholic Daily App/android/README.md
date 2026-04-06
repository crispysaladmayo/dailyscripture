# Catholic Daily App — Android (V1 scaffold)

Kotlin, **Jetpack Compose**, **Room**, **OkHttp** + **Moshi** for editorial JSON. Matches [`../catholic-daily-app-data-architecture.md`](../catholic-daily-app-data-architecture.md) and [`../catholic-daily-app-packaging-to-production.md`](../catholic-daily-app-packaging-to-production.md).

## Requirements

- **Android Studio** Koala+ (or JDK 17 + Android SDK + command-line tools).
- **SDK path:** copy `local.properties.example` → `local.properties` and set `sdk.dir`, or open the project in Android Studio (it creates `local.properties` automatically).

## Build

```bash
cd android
./gradlew :app:assembleDebug
```

**Output:** `app/build/outputs/apk/debug/app-debug.apk` — install with `adb install -r` or share the file.

### APK without local SDK (GitHub)

After you push this repo to GitHub, run workflow **“Catholic Daily App — build debug APK”** (or push a change under `Catholic Daily App/android/`). Download the **`catholic-daily-app-debug`** artifact — it contains `app-debug.apk`.

## Readings bundle in the app

1. Build a DB from the pipeline:

   ```bash
   cd ../pipeline/scripts
   python3 build_readings_bundle.py -i ../samples/readings-import.sample.json -o ../out/readings-sample.db
   ```

2. Copy the file **exactly** as:

   `app/src/main/assets/readings_bundle.db`

   (`ReadingsBundleImporter` looks for that name at app startup.)

Use **licensed** lectionary data for production; the sample is placeholders only.

## Editorial URL

`app/build.gradle.kts` sets `BuildConfig.EDITORIAL_BASE_URL`. Point it at your static CDN (see packaging doc). The app skips fetch if the host contains `example.invalid`.

## Homily (JSON feed)

`BuildConfig.HOMILY_FEED_URL` should be the **full HTTPS URL** of a static JSON file (same host as editorial is fine), e.g. `https://cdn.yourdomain.app/catholic-daily/v1/homily/latest.json`.

Shape matches [`../pipeline/samples/homily-latest.sample.json`](../pipeline/samples/homily-latest.sample.json) and `HomilyLatestJsonDto` in the app. On success, the app **replaces** the cached “latest” homily (`is_latest`) in Room.

If `HOMILY_FEED_URL` is empty, homily fetch is a no-op. Use **official or licensed** sources in production (PRD FR-9).

## Background refresh

`ContentRefreshScheduler` enqueues a **12-hour** `WorkManager` job (network required) to call `refreshHomilyIfNeeded()` and `refreshEditorial(today)` using `LiturgicalDateResolver` (default civil date in `Asia/Jakarta` until KWI logic is added).

## Package name

`com.catholicdaily.app` — change before Play upload if you use a different application id.
