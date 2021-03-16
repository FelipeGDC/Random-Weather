package com.fgdc.randomweather.data.datasource.remote.services

import com.fgdc.randomweather.data.datasource.remote.responses.WeatherApiResponse
import retrofit2.Response
import javax.inject.Inject

class WeatherService @Inject constructor(private val weatherApi: WeatherApi) : WeatherApi {

    override suspend fun getWeatherByCoordinates(
        lat: Double,
        lon: Double,
        appid: String,
        unit: String
    ): Response<WeatherApiResponse> = weatherApi.getWeatherByCoordinates(lat, lon, appid, unit)
}
