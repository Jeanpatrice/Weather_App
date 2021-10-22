package com.phatherjay.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.phatherjay.weatherapp.databinding.FragmentSelectDayBinding

class SelectDayFragment : Fragment() {

    private  var _binding : FragmentSelectDayBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )= FragmentSelectDayBinding.inflate(layoutInflater,container,false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = binding?.lvWeather


        var appBar = binding?.topAppBar?.topAppBar
        appBar?.title = "Scooby"

        appBar?.setNavigationOnClickListener() {
            // Handle navigation icon press
            findNavController().navigateUp()
        }

    }
}