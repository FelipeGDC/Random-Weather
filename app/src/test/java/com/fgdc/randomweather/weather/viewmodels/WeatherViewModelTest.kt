package com.fgdc.randomweather.weather.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fgdc.randomweather.data.repositories.WeatherRepositoryImpl
import com.fgdc.randomweather.domain.models.WeatherDomain
import com.fgdc.randomweather.domain.usecases.GetWeatherByCoordinatesUseCase
import com.fgdc.randomweather.ui.weather.mapper.WeatherViewMapper
import com.fgdc.randomweather.ui.weather.models.WeatherView
import com.fgdc.randomweather.ui.weather.viewmodel.WeatherViewModel
import com.fgdc.randomweather.utils.CoroutineTestRule
import com.fgdc.randomweather.utils.functional.Success
import com.fgdc.randomweather.utils.mockWeatherLocation
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class WeatherViewModelTest {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var getWeatherByCoordinatesUseCase: GetWeatherByCoordinatesUseCase

    private var weatherViewMapper = mock<WeatherViewMapper>()
    private var weatherRepository = mock<WeatherRepositoryImpl>()

    private val weatherResponseObserver = mock<Observer<WeatherView>>()
    private val isErrorObserver = mock<Observer<Throwable>>()

    @get:Rule
    var coroutinesRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        getWeatherByCoordinatesUseCase = GetWeatherByCoordinatesUseCase(weatherRepository)
        viewModel = WeatherViewModel(getWeatherByCoordinatesUseCase, weatherViewMapper).apply {
            weatherResponse.observeForever(weatherResponseObserver)
            failure.observeForever(isErrorObserver)
        }
    }

/*    @Test
    fun `should emit random location on success`() =
        coroutinesRule.dispatcher.runBlockingTest {
            val lat = 0.0
            val lon = 0.0
            val location =
                Success(mockWeatherLocation().toWeatherDomain())

            val channel = Channel<Success<WeatherDomain>>()
            val flow = channel.consumeAsFlow()

            doReturn(flow)
                .whenever(weatherRepository)
                .getWeatherByCoordinates(lat, lon)

            launch {
                channel.send(location)
                channel.close(IOException())
            }

            viewModel.getWeatherRandomPlace()

            verify(weatherResponseObserver).onChanged(location.data.toWeatherView())
        }*/
}
