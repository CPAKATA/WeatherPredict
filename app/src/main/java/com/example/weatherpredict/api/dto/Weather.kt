package com.example.weatherpredict.api.dto

data class Weather(
    val fact: Fact,
    val forecasts: List<Forecast>,
    val geo_object: GeoObject,
    val info: Info,
    val now: Int,
    val now_dt: String,
    val yesterday: Yesterday
)