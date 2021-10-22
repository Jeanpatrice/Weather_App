package com.phatherjay.weatherapp.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.phatherjay.weatherapp.databinding.FragmentSelectCityBinding
import com.phatherjay.weatherapp.model.query.Query
import com.phatherjay.weatherapp.viewmodel.WeatherViewModel

class SelectCityFragment : Fragment() {

    private var _binding: FragmentSelectCityBinding? = null
    private val binding get() = _binding!!
    private val weatherVM by activityViewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSelectCityBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() = with(binding) {
        editText.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            return@OnKeyListener if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                updateQuery()
                true
            } else false

        })

        btnLookup.setOnClickListener { updateQuery() }
    }


    private fun updateQuery() {
        val queryString = binding.editText.text.toString()
        if (queryString.isNotBlank()) Query(q = queryString).also { query ->
            weatherVM.query = query
            SelectCityFragmentDirections.actionSelectCityFragmentToSelectDayFragment().also {
                findNavController().navigate(it)
            }
        }
    }
}
