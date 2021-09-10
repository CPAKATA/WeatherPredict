package com.example.weatherpredict.api.requests

import com.example.weatherpredict.api.dto.Weather.CitySelect
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherCityRequests {
    @GET("weather?lang=ru&units=metric&appid=eb0c88bddc429cc23014d495b8e3d86c")
    suspend fun getByCity(
        @Query("q") cityName: String
    ): CitySelect
}