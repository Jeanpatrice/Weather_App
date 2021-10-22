package com.phatherjay.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.phatherjay.weatherapp.adapter.WeatherAdapter
import com.phatherjay.weatherapp.databinding.FragmentWeatherListBinding
import com.phatherjay.weatherapp.model.Data
import com.phatherjay.weatherapp.model.WeatherData
import com.phatherjay.weatherapp.utils.ApiState
import com.phatherjay.weatherapp.viewmodel.WeatherViewModel

class WeatherListFragment : Fragment() {

    private var _binding: FragmentWeatherListBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel by activityViewModels<WeatherViewModel>()
    private val weatherAdapter by lazy { WeatherAdapter(::weatherSelected) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentWeatherListBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        toolbar.title = weatherViewModel.query?.q?.uppercase()
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        rvWeather.adapter = weatherAdapter
    }

    private fun setupObservers() = with(weatherViewModel) {
        weatherState.observe(viewLifecycleOwner) { state ->
            binding.progressCircular.isVisible = state is ApiState.Loading
            if (state is ApiState.Success) loadForecast(state.data)
            if (state is ApiState.Failure) handleFailure(state.errorMsg)
        }
    }

    private fun loadForecast(forecast: Data) {
        weatherAdapter.clear()
        weatherAdapter.loadWeather(forecast.list)
    }

    private fun handleFailure(errorMsg: String) {
        Log.d(TAG, "ApiState.Failure: $errorMsg")
    }

    private fun weatherSelected(weather: WeatherData) {
        val action = WeatherListFragmentDirections.actionSelectDayFragmentToDetailsFragment(weather)
        findNavController().navigate(action)
    }

    companion object {
        private const val TAG = "SelectDayFragment"
    }
}