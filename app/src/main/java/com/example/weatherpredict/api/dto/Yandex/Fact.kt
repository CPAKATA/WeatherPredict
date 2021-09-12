package com.example.weatherpredict.api.dto.Yandex

data class Fact(
    val accum_prec: AccumPrec,
    val condition: String,
    val humidity: Int,
    val pressure_mm: Int,
    val temp: Int,
    val wind_dir: String,
    val wind_gust: Double,
    val wind_speed: Double
)