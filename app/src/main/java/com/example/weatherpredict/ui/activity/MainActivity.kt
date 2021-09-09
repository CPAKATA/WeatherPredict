package com.example.weatherpredict.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.weatherpredict.R
import com.example.weatherpredict.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var controller: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        controller = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.weather_now -> {
                    controller.navigate(R.id.weatherTodayFragment)
                    true
                }
                R.id.weather_week -> {
                    controller.navigate(R.id.weatherWeekFragment)
                    true
                }
                R.id.weather_geo -> {
                    controller.navigate(R.id.weatherCityFragment)
                    true
                }
                R.id.weather_somewhere -> {
                    controller.navigate(R.id.weatherCityFragment)
                    true
                }
                else -> false
            }
        }
    }
}