package com.example.android.marsrealestate

import android.app.Application
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
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
            Log.i("jaipur", "work started")
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        val repeatingRequest =
                PeriodicWorkRequestBuilder<RefreshDBWorker>(15, TimeUnit.MINUTES).build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
                RefreshDBWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest)
    }
}