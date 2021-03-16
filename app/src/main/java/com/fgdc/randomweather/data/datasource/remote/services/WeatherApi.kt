package com.fgdc.randomweather.data.datasource.remote.services

import com.fgdc.randomweather.data.datasource.remote.responses.WeatherApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    companion object {
        private const val WEATHER_ENDPOINT = "weather"
    }

    @GET(WEATHER_ENDPOINT)
    suspend fun getWeatherByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Response<WeatherApiResponse>
}
