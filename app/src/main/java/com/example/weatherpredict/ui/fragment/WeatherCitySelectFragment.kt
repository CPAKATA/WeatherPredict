package com.example.weatherpredict.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weatherpredict.R
import com.example.weatherpredict.databinding.FragmentWeatherCitySelectBinding
import com.example.weatherpredict.interfaces.TextConverter
import com.example.weatherpredict.viewmodels.WeatherCityViewModel
import com.example.weatherpredict.viewmodels.WeatherSelectViewModel


class WeatherCitySelectFragment : Fragment(),TextConverter {

    private lateinit var binding: FragmentWeatherCitySelectBinding

    private val viewModel: WeatherSelectViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        ViewModelProvider(this, WeatherSelectViewModel.Factory(activity.application))
            .get(WeatherSelectViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_city_select,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnCheck.setOnClickListener {
            binding.layout.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
            viewModel.loadSelectTemp(binding.editCity.text.toString())
            viewModel.weather.observe(viewLifecycleOwner,{
                if(it.cod == 200){
                    val text = "Погода: " + it.weather[0].description
                    binding.tvCityName.text = it.name
                    binding.tempNow.text = toCelcius(it.main.temp.toInt().toString())
                    binding.weather.text = text
                    binding.humidity.text = toHumidity(it.main.humidity.toString())
                    binding.pressure.text = toPressure(it.main.pressure.toString())
                    binding.wind.text = toWind(it.wind.speed.toString())
                    binding.loading.visibility = View.GONE
                    binding.layout1.visibility = View.VISIBLE
                }
            })
        }
        viewModel.eventNetworkError.observe(viewLifecycleOwner,{
            if(it == true){
                binding.layout.visibility = View.VISIBLE
                binding.loading.visibility = View.GONE
                binding.editCity.text.clear()
                Toast.makeText(requireContext(),"Город не найден", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun toWind(_text: String): String {
        return "Ветер: $_text м.с"
    }
}