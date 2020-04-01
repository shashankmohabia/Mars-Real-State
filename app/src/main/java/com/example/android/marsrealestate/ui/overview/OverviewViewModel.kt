package com.example.android.marsrealestate.ui.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.data.database.PropertyDatabase
import com.example.android.marsrealestate.data.models.MarsProperty
import com.example.android.marsrealestate.repository.PropertyRepository

enum class DataAvailabilityStatus { LOADING, ERROR, DONE }
enum class PropertyTypeFilter { SHOW_RENT, SHOW_BUY, SHOW_ALL }


class OverviewViewModel(application: Application) : ViewModel() {

    private val repository = PropertyRepository(PropertyDatabase.getInstance(application))

    private val _properties = MutableLiveData<LiveData<List<MarsProperty>>>()
    val properties: LiveData<LiveData<List<MarsProperty>>>
        get() = _properties

    private val _status = MutableLiveData<DataAvailabilityStatus>()
    val status: LiveData<DataAvailabilityStatus>
        get() = _status

    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
    val navigateToSelectedMarsProperty: LiveData<MarsProperty>
        get() = _navigateToSelectedProperty


    init {
        _properties.value = repository.allProperties
    }

    fun updateDataAvailabilityStatus(status: DataAvailabilityStatus) {
        _status.value = status
    }

    fun updateFilter(filter: PropertyTypeFilter) {
        _properties.value = when (filter) {
            PropertyTypeFilter.SHOW_ALL -> repository.allProperties
            PropertyTypeFilter.SHOW_BUY -> repository.buyableProperties
            PropertyTypeFilter.SHOW_RENT -> repository.rentalProperties
        }
    }

    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
    }
}
