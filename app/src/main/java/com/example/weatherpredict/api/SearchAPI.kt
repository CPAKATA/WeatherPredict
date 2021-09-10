package com.example.weatherpredict.api

import com.example.weatherpredict.api.requests.WeatherCityRequests
import com.example.weatherpredict.api.requests.WeatherRequests
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchAPI {
    private val OkHttpClient = OkHttpClient()
        .newBuilder()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.build())
        .build()

    val weather = retrofit.create(WeatherCityRequests::class.java)
}