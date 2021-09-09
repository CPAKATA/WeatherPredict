package com.example.weatherpredict.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherpredict.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeatherCityViewModel(application: Application): AndroidViewModel(application) {

    private val weatherRepository = WeatherRepository()
    private var _eventNetworkError = MutableLiveData(false)

    val weather = weatherRepository.weatherMoscow

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    fun loadTempMoscow(){
        viewModelScope.launch {
            try{
                weatherRepository.getMoscow()
                _eventNetworkError.value = false
            } catch (exception: HttpException) {
                _eventNetworkError.value = true
            }
        }
    }

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherCityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeatherCityViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}