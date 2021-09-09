package com.example.weatherpredict.repository

import androidx.lifecycle.MutableLiveData
import com.example.weatherpredict.api.API
import com.example.weatherpredict.api.dto.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository {

    val weatherMoscow: MutableLiveData<Weather> = MutableLiveData()
    val weatherGeo: MutableLiveData<Weather> = MutableLiveData()
    val weatherSelect: MutableLiveData<Weather> = MutableLiveData()
    val weatherWeek: MutableLiveData<Weather> = MutableLiveData()

    suspend fun getMoscow(){
        withContext(Dispatchers.IO){
            weatherMoscow.postValue(API.weather.getTemp("55.75396","37.620393", 1))
        }
    }

    suspend fun getWeek(){
        withContext(Dispatchers.IO){
            weatherWeek.postValue(API.weather.getTemp("55.75396","37.620393", 7))
        }
    }
}