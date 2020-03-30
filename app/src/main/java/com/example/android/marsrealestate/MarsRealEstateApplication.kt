package com.example.android.marsrealestate

import android.app.Application
import android.util.Log
import androidx.work.*
import com.example.android.marsrealestate.data.work.RefreshDBWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MarsRealEstateApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .build()

        val repeatingRequest =
                PeriodicWorkRequestBuilder<RefreshDBWorker>(1, TimeUnit.DAYS)
                        .setConstraints(constraints)
                        .build()

        Log.i("jaipur", "work scheduled")
        WorkManager.getInstance().enqueueUniquePeriodicWork(
                RefreshDBWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest)
    }
}