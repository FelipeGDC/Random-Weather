package com.fgdc.randomweather.data.repositories

import com.fgdc.randomweather.BuildConfig
import com.fgdc.randomweather.data.datasource.remote.services.WeatherApi
import com.fgdc.randomweather.utils.exception.ErrorHandler
import com.fgdc.randomweather.utils.exception.ErrorHandler.NETWORK_ERROR_MESSAGE
import com.fgdc.randomweather.utils.functional.BadRequest
import com.fgdc.randomweather.utils.functional.Error
import com.fgdc.randomweather.utils.functional.ErrorNoConnection
import com.fgdc.randomweather.utils.functional.Success
import com.fgdc.randomweather.utils.helpers.NetworkHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApi,
    private val networkHandler: NetworkHandler
) : WeatherRepository {
    override fun getWeatherByCoordinates(lat: Double, lon: Double) = flow {
        emit(
            if (networkHandler.isConnected == true) {
                apiService.getWeatherByCoordinates(lat, lon, BuildConfig.WEATHER_APP_ID, BuildConfig.WEATHER_UNIT).run {
                    if (isSuccessful && body() != null) {
                        Success(body()!!.toWeatherDomain())
                    } else {
                        BadRequest(Throwable(ErrorHandler.BAD_REQUEST))
                    }
                }
            } else {
                ErrorNoConnection(Throwable(NETWORK_ERROR_MESSAGE))
            }
        )
    }.catch {
        it.printStackTrace()
        emit(Error(Throwable(ErrorHandler.UNKNOWN_ERROR)))
    }.flowOn(Dispatchers.IO)
}
