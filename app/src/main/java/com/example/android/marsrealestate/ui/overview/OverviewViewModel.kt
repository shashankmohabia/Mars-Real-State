package com.example.android.marsrealestate.ui.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.data.database.PropertyDatabase
import com.example.android.marsrealestate.data.models.Property
import com.example.android.marsrealestate.repository.PropertyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }
enum class PropertyTypeFilter { SHOW_RENT, SHOW_BUY, SHOW_ALL }

class OverviewViewModel(application: Application) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val repository = PropertyRepository(PropertyDatabase.getInstance(application))

    var properties = repository.allProperties

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _navigateToSelectedProperty = MutableLiveData<Property>()
    val navigateToSelectedProperty: LiveData<Property>
        get() = _navigateToSelectedProperty


    init {
        refreshData()
    }

    private fun refreshData() {
        uiScope.launch {
            try {
                repository.refreshDatabase()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                if (properties.value.isNullOrEmpty()) {
                    _status.value = MarsApiStatus.ERROR
                }
            }
        }
    }

    fun updateFilter(filter: PropertyTypeFilter) {
        properties = when (filter) {
            PropertyTypeFilter.SHOW_ALL -> repository.allProperties
            PropertyTypeFilter.SHOW_BUY -> repository.buyableProperties
            PropertyTypeFilter.SHOW_RENT -> repository.rentalProperties
        }
    }

    fun displayPropertyDetails(property: Property) {
        _navigateToSelectedProperty.value = property
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
