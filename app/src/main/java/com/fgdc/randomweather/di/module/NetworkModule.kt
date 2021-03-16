package com.fgdc.randomweather.di.module

import android.content.Context
import com.fgdc.randomweather.BuildConfig
import com.fgdc.randomweather.data.datasource.remote.services.WeatherApi
import com.fgdc.randomweather.data.datasource.remote.services.WeatherService
import com.fgdc.randomweather.utils.helpers.NetworkHandler
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

    @Provides
    fun provideWeatherService(weatherApi: WeatherApi) = WeatherService(weatherApi)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .client(okHttpClient)
            .baseUrl(BuildConfig.WEATHER_API_BASE_URL)
            .build()
    }

    @Provides
    fun providesNetworkHandler(context: Context): NetworkHandler = NetworkHandler(context)
}
