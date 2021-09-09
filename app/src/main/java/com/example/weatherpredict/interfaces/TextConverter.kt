package com.example.weatherpredict.interfaces

import com.example.weatherpredict.R

interface TextConverter {
    fun toCelcius(_text: String): String{
        if(_text.toInt() > 0){
            val text = "+" + _text + "C°"
            return text
        }
        else if(_text.toInt() < 0){
            val text = "-" + _text + "C°"
            return text
        }
        val text = _text + "C°"
        return text
    }

    fun toWeather(_text: String): String{
        return "Погода: $_text"
    }

    fun toHumidity(_text: String): String{
        return "Влажность: $_text%"
    }

    fun toPressure(_text: String): String{
        return "Давление: $_text мм рт. ст."
    }

    fun toWind(_text: String,_direction: String): String{
        return "Ветер: $_text м.с/$_direction"
    }
}