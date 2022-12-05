package com.example.tp5.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5.ForecastAdapter
import com.example.tp5.R
import com.example.tp5.WeatherViewModel
import com.example.tp5.model.WeatherModel

class ForecastActivity : AppCompatActivity() {
    private val model: WeatherViewModel by viewModels()
    private lateinit var data : ArrayList<WeatherModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        val location = intent.getStringExtra("location")
        model.weatherForecast.observe(this){
            data = it
            Log.d("deglaaaaaaaaaaa","fesfeeeeeeeeeeees")
            val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@ForecastActivity)
                adapter = ForecastAdapter(data)
            }
        }
    }
}