package com.fgdc.randomweather.ui.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fgdc.randomweather.domain.models.WeatherDomain
import com.fgdc.randomweather.domain.usecases.GetWeatherByCoordinatesUseCase
import com.fgdc.randomweather.ui.base.BaseViewModel
import com.fgdc.randomweather.ui.weather.mapper.WeatherViewMapper
import com.fgdc.randomweather.ui.weather.models.WeatherView
import com.fgdc.randomweather.utils.functional.Error
import com.fgdc.randomweather.utils.functional.ErrorNoConnection
import com.fgdc.randomweather.utils.functional.Success
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val getWeatherByCoordinatesUseCase: GetWeatherByCoordinatesUseCase,
    private val weatherViewMapper: WeatherViewMapper
) : BaseViewModel() {

    var weatherResponse = MutableLiveData<WeatherView>()

    fun getWeatherRandomPlace() {
        viewModelScope.launch {
            val latitude = Math.random() * 180.0 - 90.0
            val longitude = Math.random() * 360.0 - 180.0
            getWeatherByCoordinatesUseCase(
                GetWeatherByCoordinatesUseCase.Params(
                    latitude,
                    longitude
                )
            )
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }.collect { result ->
                    when (result) {
                        is Success<WeatherDomain> -> handleSuccessGetWeather(
                            result.data
                        )
                        is Error -> handleFailure(result.exception)
                        is ErrorNoConnection -> handleFailure(result.exception)
                    }
                }
        }
    }

    private fun handleSuccessGetWeather(data: WeatherDomain) {
        val weatherView = data.toWeatherView()
        weatherView.icon = weatherViewMapper.mapToIcon(weatherView.icon)
        weatherResponse.postValue(weatherView)
    }
}
