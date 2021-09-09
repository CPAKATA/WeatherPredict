package com.example.weatherpredict.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherpredict.R
import com.example.weatherpredict.api.dto.Weather
import com.example.weatherpredict.interfaces.TextConverter
import kotlinx.android.synthetic.main.weather_item.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class RecyclerViewAdapter(private var data: Weather): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), TextConverter {

    val map = mapOf<String,String>("MONDAY" to "Понедельник","TUESDAY" to "Вторник","WEDNESDAY" to "Среда","THURSDAY" to "Четверг",
        "FRIDAY" to "Пятница","SATURDAY" to "Суббота", "SUNDAY" to "Воскресенье")


    private val items: MutableList<CardView> = mutableListOf()

    private var wasFirst = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {

        holder.days.text = getDay(data.forecasts[position].date).toString()
        holder.morning.text = toCelcius(data.forecasts[position].parts.morning.temp_avg.toString())
        holder.day.text = toCelcius(data.forecasts[position].parts.day.temp_avg.toString())
        holder.evening.text = toCelcius(data.forecasts[position].parts.evening.temp_avg.toString())
        holder.night.text = toCelcius(data.forecasts[position].parts.night.temp_avg.toString())
    }

    override fun getItemCount(): Int = data.forecasts.size

    inner class ViewHolder
    internal constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        var morning: TextView = itemView.tv_morning
        var day: TextView = itemView.tv_day
        var evening: TextView = itemView.tv_evening
        var night: TextView = itemView.tv_night
        var days: TextView = itemView.day_of_week
    }

    fun getDay(date: Date): Int{
        val c = Calendar.Builder()
            .setInstant(date)
            .setTimeZone(TimeZone.getTimeZone("Europe/Moscow"))
            .setWeekDefinition(Calendar.MONDAY,7)
            .build()
        return c.get(Calendar.DAY_OF_WEEK)
    }
}