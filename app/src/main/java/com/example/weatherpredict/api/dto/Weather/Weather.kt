package com.example.weatherpredict.api.dto.Weather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)