package com.example.android.marsrealestate.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.data.models.Property

class DetailViewModel(property: Property, app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<Property>()
    val selectedProperty: LiveData<Property>
        get() = _selectedProperty

    //move these methods to use strings formattors
    val displayPropertyType = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_type,
                app.applicationContext.getString(
                        when (it.isRental) {
                            true -> R.string.type_rent
                            false -> R.string.type_sale
                        }))
    }

    //move these methods to use strings formattors
    val displayPropertyPrice = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
                when (it.isRental) {
                    true -> R.string.display_price_monthly_rental
                    false -> R.string.display_price
                }, it.price)
    }

    init {
        _selectedProperty.value = property
    }
}
