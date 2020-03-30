package com.example.android.marsrealestate.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarsProperty(
        val id: String,
        val imgSrc: String,
        val price: Double,
        private val type: String) : Parcelable {
    val isRental
        get() = type == "rent"
}