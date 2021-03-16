package com.fgdc.randomweather.domain.usecases

import com.fgdc.randomweather.data.repositories.WeatherRepository
import com.fgdc.randomweather.domain.models.WeatherDomain
import com.fgdc.randomweather.utils.functional.State

class GetWeatherByCoordinatesUseCase(private val weatherRepository: WeatherRepository) :
    BaseUseCase<State<WeatherDomain>, GetWeatherByCoordinatesUseCase.Params>() {

    override fun run(params: Params?) =
        weatherRepository.getWeatherByCoordinates(params?.lat ?: 0.0, params?.lon ?: 0.0)

    class Params(var lat: Double, var lon: Double)
}
