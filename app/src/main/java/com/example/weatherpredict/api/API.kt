package com.example.weatherpredict.api

import com.example.weatherpredict.api.requests.WeatherRequests
import com.example.weatherpredict.repository.WeatherRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    private val OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor {
            val request = it.request().newBuilder()
            request.header("X-Yandex-API-Key", "822790d1-3b02-4275-bb6e-c24a4e3bd48f")
            it.proceed(request.build())
        }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.weather.yandex.ru/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.build())
        .build()

    val weather = retrofit.create(WeatherRequests::class.java)
}
