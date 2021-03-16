package com.fgdc.randomweather.data.datasource.remote.responses

import com.fgdc.randomweather.domain.models.WeatherDomain
import com.fgdc.randomweather.utils.extensions.empty
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherApiResponse(
    @Json(name = "base")
    var base: String?,
    @Json(name = "clouds")
    var clouds: Clouds?,
    @Json(name = "cod")
    var cod: Int?,
    @Json(name = "coord")
    var coord: Coord?,
    @Json(name = "dt")
    var dt: Long?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "main")
    var main: Main?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "snow")
    var snow: Snow?,
    @Json(name = "sys")
    var sys: Sys?,
    @Json(name = "timezone")
    var timezone: Int?,
    @Json(name = "visibility")
    var visibility: Int?,
    @Json(name = "weather")
    var weather: List<Weather>?,
    @Json(name = "wind")
    var wind: Wind?
) {
    fun toWeatherDomain() = WeatherDomain(
        coord?.lat ?: 0.0,
        coord?.lon ?: 0.0,
        weather?.get(0)?.icon ?: String.empty(),
        weather?.get(0)?.description ?: String.empty(),
        main?.temp ?: 0.0,
        main?.feelsLike ?: 0.0,
        main?.tempMax ?: 0.0,
        main?.tempMin ?: 0.0,
        name ?: String.empty(),
        sys?.country ?: String.empty(),
        ((dt!! + timezone!!) * 1000),
        (dt!! > sys!!.sunrise!! && dt!! < sys!!.sunset!!)
    )
}

@JsonClass(generateAdapter = true)
data class Clouds(
    @Json(name = "all")
    var all: Int?
)

@JsonClass(generateAdapter = true)
data class Coord(
    @Json(name = "lat")
    var lat: Double?,
    @Json(name = "lon")
    var lon: Double?
)

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "feels_like")
    var feelsLike: Double?,
    @Json(name = "grnd_level")
    var grndLevel: Int?,
    @Json(name = "humidity")
    var humidity: Int?,
    @Json(name = "pressure")
    var pressure: Int?,
    @Json(name = "sea_level")
    var seaLevel: Int?,
    @Json(name = "temp")
    var temp: Double?,
    @Json(name = "temp_max")
    var tempMax: Double?,
    @Json(name = "temp_min")
    var tempMin: Double?
)

@JsonClass(generateAdapter = true)
data class Snow(
    @Json(name = "1h")
    var h: Double?
)

@JsonClass(generateAdapter = true)
data class Sys(
    @Json(name = "country")
    var country: String?,
    @Json(name = "sunrise")
    var sunrise: Long?,
    @Json(name = "sunset")
    var sunset: Long?
)

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "description")
    var description: String?,
    @Json(name = "icon")
    var icon: String?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "main")
    var main: String?
)

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "deg")
    var deg: Int?,
    @Json(name = "gust")
    var gust: Double?,
    @Json(name = "speed")
    var speed: Double?
)
