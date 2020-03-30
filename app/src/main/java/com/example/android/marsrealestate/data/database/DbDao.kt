package com.example.android.marsrealestate.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.marsrealestate.data.models.DbProperty
import com.example.android.marsrealestate.utils.contants.DB_TABLE_NAME

@Dao
interface DbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(properties: List<DbProperty>)

    @Query("SELECT * FROM $DB_TABLE_NAME")
    fun getAllProperties(): LiveData<List<DbProperty>>

    @Query("SELECT * FROM $DB_TABLE_NAME WHERE type = 'rent'")
    fun getAllRentalProperties(): LiveData<List<DbProperty>>

    @Query("SELECT * FROM $DB_TABLE_NAME WHERE type = 'buy'")
    fun getAllBuyableProperties(): LiveData<List<DbProperty>>

    @Query("DELETE FROM $DB_TABLE_NAME")
    fun clear()

}