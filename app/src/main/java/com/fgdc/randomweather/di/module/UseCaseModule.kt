package com.fgdc.randomweather.di.module

import com.fgdc.randomweather.data.repositories.WeatherRepository
import com.fgdc.randomweather.domain.usecases.GetWeatherByCoordinatesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetWeatherByCoordinatesUseCase(weatherRepository: WeatherRepository) =
        GetWeatherByCoordinatesUseCase(weatherRepository)
}
