package com.phatherjay.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.phatherjay.weatherapp.databinding.FragmentSelectCityBinding
import com.phatherjay.weatherapp.model.query.Query
import com.phatherjay.weatherapp.viewmodel.WeatherViewModel

class SelectCityFragment: Fragment() {
    private  var _binding : FragmentSelectCityBinding? = null
    private val binding get() = _binding
    private val weatherVM by activityViewModels<WeatherViewModel>()
    private val query by lazy { Query("orlando") }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )= FragmentSelectCityBinding.inflate(layoutInflater,container,false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherVM.fectchData(query)
    }
}