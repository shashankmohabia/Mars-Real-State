package com.example.android.marsrealestate.data.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.marsrealestate.data.database.PropertyDatabase
import com.example.android.marsrealestate.repository.PropertyRepository
import retrofit2.HttpException

class RefreshDBWorker(private val context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val db = PropertyDatabase.getInstance(context)
        val repository = PropertyRepository(db)
        try {
            Log.i("jaipur", "started")
            repository.refreshDatabase()
        } catch (e: HttpException) {
            return Result.retry()
        }
        return Result.success()
    }
}