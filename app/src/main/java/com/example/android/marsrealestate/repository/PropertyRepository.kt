package com.example.android.marsrealestate.repository

import android.view.animation.Transformation
import androidx.lifecycle.Transformations
import com.example.android.marsrealestate.data.database.PropertyDatabase
import com.example.android.marsrealestate.data.models.toDomainModel
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsApiFilter
import com.example.android.marsrealestate.network.toDbModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PropertyRepository(private val propertyDatabase: PropertyDatabase) {

    val properties = Transformations.map(propertyDatabase.dao.getAllProperties()) {
        it.toDomainModel()
    }

    suspend fun refreshDatabase() {
        withContext(Dispatchers.IO) {
            //remove this filter thing afterwards
            val propertyListFromNetwork = MarsApi.retrofitService.getPropertiesAsync(MarsApiFilter.SHOW_ALL.value).await()
            propertyDatabase.dao.insertAll(propertyListFromNetwork.toDbModel())
        }
    }
}