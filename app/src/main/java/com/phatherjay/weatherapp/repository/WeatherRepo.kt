package com.phatherjay.weatherapp.repository

import com.phatherjay.weatherapp.model.query.Query

object WeatherRepo {
    private val weatherService by lazy { RetrofitInstance.weatherService }

    suspend fun getWeather(query: Query) = weatherService.getWeather(query.asQueryMap)

    private val Query.asQueryMap: Map<String, Any>
        get() = listOfNotNull("units" to units, "q" to q).toMap()
}
