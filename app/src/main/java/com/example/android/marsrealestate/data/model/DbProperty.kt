package com.example.android.marsrealestate.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.utils.contants.DB_TABLE_NAME

@Entity(tableName = DB_TABLE_NAME)
data class DbProperty(
        @PrimaryKey
        val id: String,

        @ColumnInfo(name = "image_src")
        val imgSrc: String,

        @ColumnInfo(name = "price")
        val price: Double,

        @ColumnInfo(name = "type")
        val type: String
)

fun List<DbProperty>.toDomainModel(): List<Property> {
    return map {
        Property(
                id = it.id,
                imgSrc = it.imgSrc,
                price = it.price,
                type = it.type
        )
    }
}