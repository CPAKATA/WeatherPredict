package com.example.weatherpredict.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weatherpredict.R
import com.example.weatherpredict.databinding.FragmentWeatherCityBinding
import com.example.weatherpredict.databinding.FragmentWeatherTodayBinding
import com.example.weatherpredict.interfaces.TextConverter
import com.example.weatherpredict.viewmodels.WeatherCityViewModel


class WeatherTodayFragment : Fragment(),TextConverter {

    private lateinit var binding: FragmentWeatherTodayBinding

    private val viewModel: WeatherCityViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        ViewModelProvider(this, WeatherCityViewModel.Factory(activity.application))
            .get(WeatherCityViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_today,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadTempMoscow()
        viewModel.weather.observe(viewLifecycleOwner,{
            binding.tempNow.text = toCelcius(it.fact.temp.toString())
            binding.tvMorningTemp.text = toCelcius(it.forecasts[0].parts.morning.temp_avg.toString())
            binding.tvDayTemp.text = toCelcius(it.forecasts[0].parts.day.temp_avg.toString())
            binding.tvEveningTemp.text = toCelcius(it.forecasts[0].parts.evening.temp_avg.toString())
            binding.tvNightTemp.text = toCelcius(it.forecasts[0].parts.night.temp_avg.toString())
            binding.weather.text = toWeather(it.fact.condition)
            binding.humidity.text = toHumidity(it.fact.humidity.toString())
            binding.pressure.text = toPressure(it.fact.pressure_mm.toString())
            binding.wind.text = toWind(it.fact.wind_speed.toString(),it.fact.wind_dir)
        })
    }
}