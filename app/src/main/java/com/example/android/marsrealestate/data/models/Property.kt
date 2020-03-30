package com.example.android.marsrealestate.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Property(
        val id: String,
        val imgSrc: String,
        val price: Double,
        val type: String) : Parcelable {
    val isRental
        get() = type == "rent"
}