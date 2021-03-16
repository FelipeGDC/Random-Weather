package com.fgdc.randomweather.weather

import com.fgdc.randomweather.BuildConfig
import com.fgdc.randomweather.data.datasource.remote.responses.WeatherApiResponse
import com.fgdc.randomweather.data.datasource.remote.services.WeatherService
import com.fgdc.randomweather.data.repositories.WeatherRepositoryImpl
import com.fgdc.randomweather.domain.models.WeatherDomain
import com.fgdc.randomweather.utils.functional.State
import com.fgdc.randomweather.utils.functional.Success
import com.fgdc.randomweather.utils.helpers.NetworkHandler
import com.fgdc.randomweather.utils.mockWeatherLocation
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import retrofit2.Response

class WeatherRepositoryTest {

    @Test
    fun `should get random location on success`(): Unit = runBlocking {

        val location: WeatherApiResponse = mockWeatherLocation()
        val lat = 0.0
        val lon = 0.0
        val service = mock<WeatherService> {
            onBlocking {
                getWeatherByCoordinates(
                    lat,
                    lon,
                    BuildConfig.WEATHER_APP_ID,
                    BuildConfig.WEATHER_UNIT
                )
            } doReturn Response.success(location)
        }
        service.getWeatherByCoordinates(
            lat, lon, BuildConfig.WEATHER_APP_ID, BuildConfig.WEATHER_UNIT
        ).body() shouldBeEqualTo location

        val networkHandler = mock<NetworkHandler> {
            onBlocking { isConnected } doReturn true
        }
        val repository = WeatherRepositoryImpl(
            service,
            networkHandler
        )
        val flow: Flow<State<WeatherDomain>> =
            repository.getWeatherByCoordinates(lat, lon)
        flow.collect { result ->
            result.`should be instance of`<Success<WeatherDomain>>()
            when (result) {
                is Success<WeatherDomain> -> {
                    result.data shouldBeEqualTo location.toWeatherDomain()
                }
            }
        }
    }
}
