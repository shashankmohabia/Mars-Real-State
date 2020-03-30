package com.example.android.marsrealestate.data.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.marsrealestate.data.database.PropertyDatabase
import com.example.android.marsrealestate.repository.PropertyRepository
import retrofit2.HttpException

class RefreshDBWorker(private val context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME = "com.example.android.marsrealestate.data.work.RefreshDBWorker"
    }

    override suspend fun doWork(): Result {
        Log.i("jaipur", "work started")
        val db = PropertyDatabase.getInstance(context)
        val repository = PropertyRepository(db)
        try {
            repository.refreshDatabase()
            Log.i("jaipur", "work done")
        } catch (e: HttpException) {
            Log.i("jaipur", "work gave error")
            return Result.retry()
        }
        return Result.success()
    }
}