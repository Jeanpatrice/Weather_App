package com.phatherjay.weatherapp.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Wind(
    @Json(name = "deg")
    val deg: Int?,
    @Json(name = "gust")
    val gust: Float?,
    @Json(name = "speed")
    val speed: Float?
):Parcelable