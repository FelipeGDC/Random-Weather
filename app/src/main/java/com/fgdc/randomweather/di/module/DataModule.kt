package com.fgdc.randomweather.di.module

import com.fgdc.randomweather.data.datasource.remote.services.WeatherApi
import com.fgdc.randomweather.data.repositories.WeatherRepository
import com.fgdc.randomweather.data.repositories.WeatherRepositoryImpl
import com.fgdc.randomweather.utils.helpers.NetworkHandler
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        networkHandler: NetworkHandler
    ): WeatherRepository = WeatherRepositoryImpl(weatherApi, networkHandler)
}
