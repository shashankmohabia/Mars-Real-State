package com.example.android.marsrealestate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.marsrealestate.data.model.DbProperty
import com.example.android.marsrealestate.utils.contants.DB_NAME

@Database(entities = [DbProperty::class], version = 1, exportSchema = false)
abstract class PropertyDatabase : RoomDatabase() {

    abstract val dao: DbDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: PropertyDatabase

        fun getInstance(context: Context): PropertyDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            PropertyDatabase::class.java,
                            DB_NAME
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }
    }
}