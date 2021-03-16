package com.fgdc.randomweather.domain.models

import com.fgdc.randomweather.ui.weather.models.WeatherView

data class WeatherDomain(
    var lat: Double,
    var lon: Double,
    var icon: String,
    var description: String,
    var temp: Double,
    var feelsLike: Double,
    var tempMax: Double,
    var tempMin: Double,
    var name: String,
    var country: String,
    var time: Long,
    var isDay: Boolean
) {
    fun toWeatherView() = WeatherView(
        lat,
        lon,
        icon,
        description,
        temp,
        feelsLike,
        tempMax,
        tempMin,
        name,
        country,
        time,
        isDay
    )
}
