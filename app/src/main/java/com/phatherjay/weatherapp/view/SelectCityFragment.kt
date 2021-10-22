package com.phatherjay.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import com.phatherjay.weatherapp.databinding.FragmentSelectCityBinding
import com.phatherjay.weatherapp.model.query.Query
import com.phatherjay.weatherapp.viewmodel.WeatherViewModel

class SelectCityFragment: Fragment() {
    private  var _binding : FragmentSelectCityBinding? = null
    private val binding get() = _binding
    private val weatherVM by activityViewModels<WeatherViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )= FragmentSelectCityBinding.inflate(layoutInflater,container,false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        fun myEnter() {
            binding?.editText?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                    passThru()
                    return@OnKeyListener true
                } else {
                    false
                }
            })
        }
        myEnter()

        binding?.btnLookup?.setOnClickListener {passThru()}
    }


    private fun passThru() {
        val query = Query(binding?.editText?.text.toString())
        if (query.q != ""){
            weatherVM.fectchData(query)
            findNavController().navigate(SelectCityFragmentDirections.actionSelectCityFragmentToSelectDayFragment())
        }
    }

    companion object{
        const val TAG = "SelectionLocation"
    }
}