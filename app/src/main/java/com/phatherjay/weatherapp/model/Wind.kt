package com.phatherjay.weatherapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "deg")
    val deg: Int?,
    @Json(name = "gust")
    val gust: Float?,
    @Json(name = "speed")
    val speed: Float?
)