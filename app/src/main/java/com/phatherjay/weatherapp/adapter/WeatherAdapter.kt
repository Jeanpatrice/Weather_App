package com.phatherjay.weatherapp.adapter

import android.content.Context
import android.database.DataSetObserver
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.phatherjay.weatherapp.model.WeatherData

class WeatherAdapter(private val weatherList: MutableList<WeatherData> = mutableListOf(),
                     private val context: Context): BaseAdapter() {

    override fun getCount(): Int {
        return weatherList.size
    }

    override fun getItem(p0: Int): Any {
        return "Test String"
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val textView = TextView(context)
        textView.text = "Hellr"
        return textView
    }

}