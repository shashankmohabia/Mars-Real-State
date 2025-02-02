/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.data.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class NetworkMarsProperty(
        val id: String,
        @Json(name = "img_src") val imgSrc: String,
        val price: Double,
        val type: String) : Parcelable {
    val isRental
        get() = type == "rent"
}

fun List<NetworkMarsProperty>.toDbModel(): List<DbMarsProperty> {
    return map {
        DbMarsProperty(
                id = it.id,
                imgSrc = it.imgSrc,
                price = it.price,
                type = it.type
        )
    }
}
