package com.fgdc.randomweather.ui.weather.mapper

import javax.inject.Inject

class WeatherViewMapper @Inject constructor() {

    internal fun mapToIcon(iconId: String): String {
        return when (iconId) {
            "01d" -> "day_clear_sky"
            "01n" -> "night_clear_sky"
            "02d" -> "day_few_clouds"
            "02n" -> "night_few_clouds"
            "03d" -> "day_scattered_clouds"
            "03n" -> "night_scattered_clouds"
            "04d" -> "day_broken_clouds"
            "04n" -> "night_broken_clouds"
            "09d" -> "day_shower_rains"
            "09n" -> "night_shower_rains"
            "10d" -> "day_rain"
            "10n" -> "night_rain"
            "11d" -> "day_thunderstorm"
            "11n" -> "night_thunderstorm"
            "13d" -> "day_snow"
            "13n" -> "night_snow"
            "50d" -> "day_mist"
            "50n" -> "night_mist"
            else -> ""
        }
    }
}
