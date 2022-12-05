package com.example.tp5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5.model.WeatherModel

class ForecastAdapter(private val forecastData : ArrayList<WeatherModel>):
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tempTextView: TextView
        val overcastTextView: TextView
        init {
            tempTextView = itemView.findViewById(R.id.tempItem)
            overcastTextView = itemView.findViewById(R.id.overcastItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tempTextView.text = forecastData[position].temp.toString()
        holder.overcastTextView.text = forecastData[position].overcast
    }

    override fun getItemCount(): Int {
        return forecastData.size
    }
}