package com.phatherjay.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.phatherjay.weatherapp.databinding.FragmentDetailsBinding
import com.phatherjay.weatherapp.viewmodel.WeatherViewModel

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel by activityViewModels<WeatherViewModel>()
    private val weather by lazy { navArgs<DetailsFragmentArgs>().value.weather }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailsBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appBar = binding.topAppBar.topAppBar
        appBar.title = weatherViewModel.query?.q?.uppercase()
        appBar.setNavigationOnClickListener() {
            // Handle navigation icon press
            findNavController().navigateUp()
        }
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView()= with(binding){
        tvTemp.text = weather.main?.temp.toString()
        tvDescription.text = weather.weather?.get(0)?.description
        val feels = "Feels Like: ${weather.main?.feelsLike}"
        tvFeelLike.text = feels
        tvForecast.text = weather.weather?.get(0)?.main
    }
}