package com.phatherjay.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phatherjay.weatherapp.model.Data
import com.phatherjay.weatherapp.model.query.Query
import com.phatherjay.weatherapp.repository.WeatherRepo
import com.phatherjay.weatherapp.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherState = MutableLiveData<ApiState<Data>>(ApiState.Loading)
    val weatherState: LiveData<ApiState<Data>>
        get() = _weatherState

    var query: Query? = null
        set(value) {
            value?.let { fetchWeather(it) }
            field = value
        }

    private fun fetchWeather(query: Query) {
        viewModelScope.launch(Dispatchers.IO) {
            WeatherRepo.getWeather(query).let { response ->
                val state = if (response.isSuccessful || response.body() != null)
                    ApiState.Success(response.body()!!)
                else ApiState.Failure("Weather body is null")
                _weatherState.postValue(state)
            }
        }
    }
}