package com.phatherjay.weatherapp.repository

import com.phatherjay.weatherapp.model.Data
import com.phatherjay.weatherapp.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface WeatherService {

    @Headers("appid: 65d00499677e59496ca2f318eb68c049")
    @GET("data/2.5/forecast?appid=65d00499677e59496ca2f318eb68c049")
    suspend fun getWeather(
        @QueryMap options: Map<String, @JvmSuppressWildcards Any>
    ): Response<Data>

}