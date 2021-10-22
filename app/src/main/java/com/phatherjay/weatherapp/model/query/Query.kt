package com.phatherjay.weatherapp.model.query

data class Query(
    var q: String,
    val units: String = "imperial"
)