package com.example.android.marsrealestate

import android.app.Application
import androidx.work.PeriodicWorkRequestBuilder
import com.example.android.marsrealestate.data.work.RefreshDBWorker
import java.util.concurrent.TimeUnit

class MarsRealEstateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    private fun setupRecurringWork() {
        val repeatingRequest =
                PeriodicWorkRequestBuilder<RefreshDBWorker>(1, TimeUnit.DAYS).build()
    }
}