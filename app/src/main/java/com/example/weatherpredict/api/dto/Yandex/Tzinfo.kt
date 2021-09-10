package com.example.weatherpredict.api.dto.Yandex

data class Tzinfo(
    val abbr: String,
    val dst: Boolean,
    val name: String,
    val offset: Int
)