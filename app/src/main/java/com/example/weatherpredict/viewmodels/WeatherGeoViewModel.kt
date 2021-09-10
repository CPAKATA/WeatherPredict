package com.example.weatherpredict.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherpredict.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeatherGeoViewModel(application: Application): AndroidViewModel(application) {

    private val weatherRepository = WeatherRepository()
    private var _eventNetworkError = MutableLiveData(false)

    val weather = weatherRepository.weatherGeo

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    fun loadTempWeek(lat: String, lon: String){
        viewModelScope.launch {
            try{
                weatherRepository.getPlace(lat,lon)
                _eventNetworkError.value = false
            } catch (exception: HttpException) {
                _eventNetworkError.value = true
            }
        }
    }

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherGeoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeatherGeoViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}