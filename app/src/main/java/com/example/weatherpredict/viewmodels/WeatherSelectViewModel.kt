package com.example.weatherpredict.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.weatherpredict.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeatherSelectViewModel(application: Application): AndroidViewModel(application) {

    private val weatherRepository = WeatherRepository()
    private var _eventNetworkError = MutableLiveData(false)

    val weather = weatherRepository.weatherSelect

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    fun loadSelectTemp(city: String){
        viewModelScope.launch {
            try{
                weatherRepository.getByName(city)
                _eventNetworkError.value = false
            } catch (exception: HttpException) {
                _eventNetworkError.value = true
            }
        }
    }

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherSelectViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeatherSelectViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}