package com.phatherjay.weatherapp.repository

import android.util.Log
import com.phatherjay.weatherapp.model.query.Query
import com.phatherjay.weatherapp.utils.ApiState
import kotlinx.coroutines.flow.flow

object WeatherRepo {
    private val weatherService by lazy { RetrofitInstance.weatherService }
    private const val TAG = "Weather Repo"


    fun getWeather(query: Query) = flow {
        emit(ApiState.Loading)

        val state = if (query.q != null) {
            val weatherResponse = weatherService.getWeather(query.asQueryMap)
            Log.e(TAG, "${weatherResponse.body()}")
            if (weatherResponse.isSuccessful){
                Log.e(TAG, "Response is successful $query")
                if (weatherResponse.body() == null){
                    ApiState.Failure("Weather body is null")
                }else {
                    Log.e(TAG, "getWeatherState: Success")
                    ApiState.Success(weatherResponse.body())
                }
            } else {
                Log.e(TAG, "getWeatherState is a failure $query")
                ApiState.Failure("error fetching data")
            }
        }else ApiState.Failure("Endpoint is null")
        emit(state)
    }

    private val Query.asQueryMap: Map<String, Any>
        get() = listOfNotNull(
            "units" to units,
            q?.let { "q" to it }
        ).toMap()

}
