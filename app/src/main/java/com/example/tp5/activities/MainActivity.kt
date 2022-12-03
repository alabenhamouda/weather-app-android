package com.example.tp5.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tp5.WeatherViewModel
import com.example.tp5.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val model: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val locations = listOf<String>("Tunis", "London", "Paris", "Mozambique", "Brazil")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.weather.observe(this) {
            this@MainActivity.binding.temp.text = it.temp.toString() + "°C"
            this@MainActivity.binding.overcast.text = it.overcast
            this@MainActivity.binding.humidityValue.text = it.humidity.toString()
            this@MainActivity.binding.pressureValue.text = it.pressure.toString()
            Picasso.get().load(it.iconUrl).into(binding.imageView)
        }

        binding.location.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, this.locations)
        binding.location.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val location = this@MainActivity.locations[position]
                model.location = location
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "Degla fsfs", Toast.LENGTH_SHORT).show()
            }
        }

    }
}