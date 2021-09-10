package com.example.weatherpredict.api.dto.Yandex

data class GeoObject(
    val country: Country,
    val district: District,
    val locality: Locality,
    val province: Province
)