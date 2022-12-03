package com.example.tp5

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp5.model.WeatherModel
import com.example.tp5.model.apiResponses.ForecastWeatherResponse
import com.example.tp5.model.apiResponses.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel: ViewModel() {
    var location: String = "Tunis"
    set(value) {
        this.updateWeather(value)
    }
    val weather: MutableLiveData<WeatherModel> by lazy { MutableLiveData<WeatherModel>() }

    val weatherForecast: MutableLiveData<ArrayList<WeatherModel>> by lazy { MutableLiveData<ArrayList<WeatherModel>>()}

    fun updateWeather(location: String){
        RetrofitHelper.RetrofitHelper.retrofitService.getWeather(location).enqueue(object:
            Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val weatherResponse: WeatherResponse = response.body()!!
                    val iconUrl = "https://openweathermap.org/img/wn/${weatherResponse.weather[0].icon}@2x.png"
                    weather.value = WeatherModel(
                        weatherResponse.main.temp,
                        weatherResponse.weather[0].description,
                        weatherResponse.main.humidity.toDouble(),
                        weatherResponse.main.pressure.toDouble(),
                        iconUrl = iconUrl
                    )
                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable?) {
                Log.d("Failure", t.toString())
            }
        })
    }

    fun getWeatherForecast(location: String) {
        RetrofitHelper.RetrofitHelper.retrofitService.getForecast(location).enqueue(object:
            Callback<ForecastWeatherResponse> {
            override fun onResponse(call: Call<ForecastWeatherResponse>, response: Response<ForecastWeatherResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val forecastResponse: ForecastWeatherResponse = response.body()!!
                    val list = forecastResponse.list
                    val forecastList: ArrayList<WeatherModel> = ArrayList<WeatherModel>()
                    list.forEach() {
                        val iconUrl = "https://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png"
                        forecastList.add(WeatherModel(
                            it.main.temp,
                            it.weather[0].description,
                            it.main.humidity.toDouble(),
                            it.main.pressure.toDouble(),
                            iconUrl
                        ))
                    }
                    weatherForecast.value = forecastList
                }
            }
            override fun onFailure(call: Call<ForecastWeatherResponse>, t: Throwable?) {
                Log.d("Failure", t.toString())
            }
        })
    }
}