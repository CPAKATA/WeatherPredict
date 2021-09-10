package com.example.weatherpredict.api.dto.Yandex

data class Info(
    val _h: Boolean,
    val def_pressure_mm: Int,
    val def_pressure_pa: Int,
    val f: Boolean,
    val geoid: Int,
    val lat: Double,
    val lon: Double,
    val n: Boolean,
    val nr: Boolean,
    val ns: Boolean,
    val nsr: Boolean,
    val p: Boolean,
    val slug: String,
    val tzinfo: Tzinfo,
    val url: String,
    val zoom: Int
)