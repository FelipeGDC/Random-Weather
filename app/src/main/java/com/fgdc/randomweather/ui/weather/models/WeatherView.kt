package com.fgdc.randomweather.ui.weather.models

data class WeatherView(
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
)
