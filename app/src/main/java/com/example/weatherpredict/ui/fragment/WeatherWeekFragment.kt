package com.example.weatherpredict.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherpredict.R
import com.example.weatherpredict.adapters.RecyclerViewAdapter
import com.example.weatherpredict.databinding.FragmentWeatherTodayBinding
import com.example.weatherpredict.databinding.FragmentWeatherWeekBinding
import com.example.weatherpredict.viewmodels.WeatherCityViewModel
import com.example.weatherpredict.viewmodels.WeatherWeekViewModel


class WeatherWeekFragment : Fragment() {

    private lateinit var binding: FragmentWeatherWeekBinding

    private val viewModel: WeatherWeekViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        ViewModelProvider(this, WeatherWeekViewModel.Factory(activity.application))
            .get(WeatherWeekViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_week,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        viewModel.loadTempWeek()
        viewModel.weather.observe(viewLifecycleOwner,{
            binding.recyclerView.adapter = RecyclerViewAdapter(it)
            binding.loading.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        })

    }
}