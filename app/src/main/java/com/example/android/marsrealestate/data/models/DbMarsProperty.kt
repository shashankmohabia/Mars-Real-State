package com.example.android.marsrealestate.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.marsrealestate.utils.contants.DB_TABLE_NAME

@Entity(tableName = DB_TABLE_NAME)
data class DbMarsProperty(
        @PrimaryKey
        val id: String,

        @ColumnInfo(name = "image_src")
        val imgSrc: String,

        @ColumnInfo(name = "price")
        val price: Double,

        @ColumnInfo(name = "type")
        val type: String
)

fun List<DbMarsProperty>.toDomainModel(): List<MarsProperty> {
    return map {
        MarsProperty(
                id = it.id,
                imgSrc = it.imgSrc,
                price = it.price,
                type = it.type
        )
    }
}