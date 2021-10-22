package com.phatherjay.weatherapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phatherjay.weatherapp.databinding.ItemWeatherBinding
import com.phatherjay.weatherapp.model.WeatherData


class WeatherAdapter(
    private val weatherListener: (WeatherData) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val weatherList: MutableList<WeatherData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherViewHolder.getInstance(parent).also { holder ->
            holder.itemView.setOnClickListener { weatherListener(weatherList[holder.adapterPosition]) }
        }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.loadForecast(weatherList[position])
    }

    override fun getItemCount() = weatherList.size

    fun loadWeather(weather: List<WeatherData>) {
        Log.e(TAG, "updatedList :$weather)")
        val startIndex = weatherList.size
        weatherList.addAll(weather)
        notifyItemRangeInserted(startIndex, weather.size)
    }

    fun clear() {
        val listSize = weatherList.size
        weatherList.clear()
        notifyItemRangeRemoved(0, listSize)
    }


    class WeatherViewHolder(
        private val binding: ItemWeatherBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun loadForecast(weather: WeatherData) = with(binding) {
            tvWeatherName.text = weather.weather?.firstOrNull()?.main
            val temp  = "Temp is: ${weather.main?.temp}"
            tvWeatherTemp.text = temp
        }


        companion object {
            fun getInstance(parent: ViewGroup): WeatherViewHolder {
                val binding =
                    ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return WeatherViewHolder(binding)
            }
        }
    }

    companion object {
        private const val TAG = "WeatherAdapter"
    }
}