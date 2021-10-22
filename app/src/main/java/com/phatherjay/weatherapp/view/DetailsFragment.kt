package com.phatherjay.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.phatherjay.weatherapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private  var _binding : FragmentDetailsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )= FragmentDetailsBinding.inflate(layoutInflater,container,false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var appBar = binding?.topAppBar?.topAppBar


        appBar?.setNavigationOnClickListener() {
            // Handle navigation icon press
            findNavController().navigateUp()
        }

    }

}