package com.example.weatherpredict.api.requests

import com.example.weatherpredict.api.dto.Weather
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherRequests {
    @GET("forecast?&hours=false&lang=RU")
    suspend fun getTemp(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("days") days: Int): Weather
}