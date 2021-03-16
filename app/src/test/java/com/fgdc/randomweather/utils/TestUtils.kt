package com.fgdc.randomweather.utils

import com.fgdc.randomweather.data.datasource.remote.responses.*
import com.fgdc.randomweather.utils.extensions.empty

fun mockWeatherLocation() = WeatherApiResponse(
    String.empty(),
    Clouds(0),
    0,
    Coord(0.0, 0.0),
    0,
    0,
    Main(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0),
    String.empty(),
    Snow(0.0),
    Sys(String.empty(), 0, 0),
    0,
    0,
    listOf(Weather(String.empty(), "01d", 0, String.empty())),
    Wind(0, 0.0, 0.0)
)
