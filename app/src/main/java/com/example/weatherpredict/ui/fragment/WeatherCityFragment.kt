package com.example.weatherpredict.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.weatherpredict.R
import com.example.weatherpredict.databinding.FragmentWeatherCityBinding
import com.example.weatherpredict.ui.activity.MainActivity

import android.location.LocationManager

import android.content.Context.LOCATION_SERVICE
import android.location.Geocoder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.weatherpredict.interfaces.TextConverter
import com.example.weatherpredict.viewmodels.WeatherCityViewModel
import com.example.weatherpredict.viewmodels.WeatherGeoViewModel
import com.google.android.gms.location.*
import java.lang.Exception
import java.util.*


class WeatherCityFragment : Fragment(), TextConverter{

    private val viewModel: WeatherGeoViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        ViewModelProvider(this, WeatherGeoViewModel.Factory(activity.application))
            .get(WeatherGeoViewModel::class.java)
    }

    private lateinit var binding: FragmentWeatherCityBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var geocoder: Geocoder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_city,
            container,
            false
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        getLastKnownLocation()
    }

    fun getLastKnownLocation() {
        val task = fusedLocationClient.lastLocation

        if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
        task.addOnSuccessListener {
            if(it!=null){
                viewModel.loadTempWeek(it.latitude.toString(),it.longitude.toString())
                viewModel.weather.observe(viewLifecycleOwner,{
                    binding.loading.visibility = View.GONE
                    binding.layout.visibility = View.VISIBLE
                    binding.tvCityName.text = it.info.tzinfo.name
                    binding.tvTemp.text = toCelcius(it.fact.temp.toString())
                    binding.tvMorningTemp.text = toCelcius(it.forecasts[0].parts.morning.temp_avg.toString())
                    binding.tvDayTemp.text = toCelcius(it.forecasts[0].parts.day.temp_avg.toString())
                    binding.tvEveningTemp.text = toCelcius(it.forecasts[0].parts.evening.temp_avg.toString())
                    binding.tvNightTemp.text = toCelcius(it.forecasts[0].parts.night.temp_avg.toString())
                    binding.tvWeather.text = toWeather(it.fact.condition)
                    binding.tvHumidity.text = toHumidity(it.fact.humidity.toString())
                    binding.tvPressure.text = toPressure(it.fact.pressure_mm.toString())
                    binding.tvWind.text = toWind(it.fact.wind_speed.toString(),it.fact.wind_dir)
                })
            }
        }
    }

}