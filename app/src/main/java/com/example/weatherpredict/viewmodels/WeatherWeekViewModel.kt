package com.example.weatherpredict.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherpredict.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeatherWeekViewModel(application: Application): AndroidViewModel(application) {

    private val weatherRepository = WeatherRepository()
    private var _eventNetworkError = MutableLiveData(false)

    val weather = weatherRepository.weatherWeek

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    fun loadTempWeek(){
        viewModelScope.launch {
            try{
                weatherRepository.getWeek()
                _eventNetworkError.value = false
            } catch (exception: HttpException) {
                _eventNetworkError.value = true
            }
        }
    }

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherWeekViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeatherWeekViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}