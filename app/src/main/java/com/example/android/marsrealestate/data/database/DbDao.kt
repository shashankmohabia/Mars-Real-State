package com.example.android.marsrealestate.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.marsrealestate.data.models.DbMarsProperty
import com.example.android.marsrealestate.utils.contants.DB_TABLE_NAME

@Dao
interface DbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(marsProperties: List<DbMarsProperty>)

    @Query("SELECT * FROM $DB_TABLE_NAME")
    fun getAllProperties(): LiveData<List<DbMarsProperty>>

    @Query("SELECT * FROM $DB_TABLE_NAME WHERE type = 'rent'")
    fun getAllRentalProperties(): LiveData<List<DbMarsProperty>>

    @Query("SELECT * FROM $DB_TABLE_NAME WHERE type = 'buy'")
    fun getAllBuyableProperties(): LiveData<List<DbMarsProperty>>

    @Query("DELETE FROM $DB_TABLE_NAME")
    fun clear()

}