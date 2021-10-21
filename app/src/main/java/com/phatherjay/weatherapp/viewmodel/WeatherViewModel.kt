package com.phatherjay.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phatherjay.weatherapp.model.Data
import com.phatherjay.weatherapp.model.WeatherData
import com.phatherjay.weatherapp.model.query.Query
import com.phatherjay.weatherapp.repository.WeatherRepo
import com.phatherjay.weatherapp.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    private val _weatherState = MutableLiveData<ApiState<WeatherData>>()
    val wheatherState: LiveData<ApiState<WeatherData>>
        get() = _weatherState

    var query : Query? = null

    fun fectchData(query: Query){
        this.query = query
        fetchWeather(query)
    }
    private fun fetchWeather(query: Query){
        viewModelScope.launch(Dispatchers.IO){
            WeatherRepo.getWeather(query).collect {
                _weatherState.postValue(it as ApiState<WeatherData>?)
            }
        }
    }
}