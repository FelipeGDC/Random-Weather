package com.fgdc.randomweather.data.repositories

import com.fgdc.randomweather.domain.models.WeatherDomain
import com.fgdc.randomweather.utils.functional.State
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getWeatherByCoordinates(lat: Double, lon: Double): Flow<State<WeatherDomain>>
}
