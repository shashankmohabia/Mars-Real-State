package com.example.android.marsrealestate.repository

import androidx.lifecycle.Transformations
import com.example.android.marsrealestate.data.database.PropertyDatabase
import com.example.android.marsrealestate.data.models.toDomainModel
import com.example.android.marsrealestate.data.network.MarsApi
import com.example.android.marsrealestate.data.models.toDbModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PropertyRepository(private val propertyDatabase: PropertyDatabase) {

    val allProperties by lazy {
        Transformations.map(propertyDatabase.dao.getAllProperties()) {
            it.toDomainModel()
        }
    }

    val rentalProperties by lazy {
        Transformations.map(propertyDatabase.dao.getAllRentalProperties()) {
            it.toDomainModel()
        }
    }

    val buyableProperties by lazy {
        Transformations.map(propertyDatabase.dao.getAllBuyableProperties()) {
            it.toDomainModel()
        }
    }

    suspend fun refreshDatabase() {
        withContext(Dispatchers.IO) {
            //remove this filter thing afterwards
            val propertyListFromNetwork = MarsApi.retrofitService.getPropertiesAsync().await()
            propertyDatabase.dao.insertAll(propertyListFromNetwork.toDbModel())
        }
    }
}