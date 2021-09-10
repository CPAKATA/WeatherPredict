package com.example.weatherpredict.interfaces

import com.example.weatherpredict.R

interface TextConverter {

    val conditions: Map<String, String>
        get() = mapOf<String,String>("clear" to "Ясно",
        "partly-cloudy" to "Малооблачно",
        "cloudy" to "облачно с прояснениями",
        "overcast" to "пасмурно",
        "drizzle" to "морось",
        "light-rain" to "небольшой дождь",
        "rain" to "дождь",
        "moderate-rain" to "умеренно сильный дождь",
        "heavy-rain" to "сильный дождь",
        "continuous-heavy-rain" to "длительный сильный дождь",
        "showers" to "ливень",
        "wet-snow" to "дождь со снегом",
        "light-snow" to "небольшой снег",
        "snow" to "снег",
        "snow-showers" to "снегопад",
        "hail" to "град",
        "thunderstorm" to "гроза",
        "thunderstorm-with-rain" to "дождь с грозой",
        "thunderstorm-with-hail" to "гроза с градом")

    val wind: Map<String,String>
        get() = mapOf("nw" to "северо-западный",
            "n" to "северный",
            "ne" to "северо-восточный",
            "e" to "восточный",
            "se" to "юго-восточный",
            "s" to "южный",
            "sw" to "юго-западный",
            "w" to "западный",
            "с" to "штиль")

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
        return "Погода: " + conditions.get(_text)
    }

    fun toHumidity(_text: String): String{
        return "Влажность: $_text%"
    }

    fun toPressure(_text: String): String{
        return "Давление: $_text мм рт. ст."
    }

    fun toWind(_text: String,_direction: String): String{
        return "Ветер: $_text м.с/" + wind.get(_direction)
    }
}