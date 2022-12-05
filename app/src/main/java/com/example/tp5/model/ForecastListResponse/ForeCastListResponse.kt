package com.example.tp5.model.ForecastListResponse

data class ForeCastListResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastListItem>,
    val message: Double
)