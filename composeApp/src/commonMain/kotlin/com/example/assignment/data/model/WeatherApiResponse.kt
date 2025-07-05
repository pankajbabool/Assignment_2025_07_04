package com.example.assignment.data.model

import com.example.assignment.data.enums.WeatherCode
import com.example.assignment.extensions.formatLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class WeatherApiResponse(
    val reason: String? = null,
    val error: Boolean? = null,

    val current: Current = Current(),
    val current_units: CurrentUnits = CurrentUnits(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
) {

    @Serializable
    data class Current(
        val temperature_2m: Double = 0.0,
        val time: String = "",
        val weather_code: Int = -1,
        val wind_speed_10m: Double = 0.0
    )

    @Serializable
    data class CurrentUnits(
        val temperature_2m: String = "",
        val weather_code: String = "",
        val wind_speed_10m: String = ""
    )
}

@Serializable
data class WeatherData(
    val currentTemperature: String,
    val currentWeatherDescription: String,
    val currentWindSpeed: String,
    val updatedTime: String,
    val weatherCode: Int = -1,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

fun WeatherApiResponse.toWeatherData(): WeatherData {
    val responseData = this

    val latitude = responseData.latitude
    val longitude = responseData.longitude

    val currentTemperature = responseData.current.temperature_2m
    val currentTemperatureUnits = responseData.current_units.temperature_2m
    val currentTemperatureWithUnits = "$currentTemperature $currentTemperatureUnits"

    val currentWindSpeed = responseData.current.wind_speed_10m
    val currentWindSpeedUnits = responseData.current_units.wind_speed_10m
    val currentWindSpeedWithUnits = "$currentWindSpeed $currentWindSpeedUnits"


    val updatedTime = responseData.current.time
    val formattedTime = formatLocalDateTime(updatedTime)

    val weatherCode = responseData.current.weather_code
    val weatherDescription = WeatherCode.fromCode(weatherCode)?.description.toString()

    val weatherData = WeatherData(
        currentTemperature = currentTemperatureWithUnits,
        currentWeatherDescription = weatherDescription,
        currentWindSpeed = currentWindSpeedWithUnits,
        updatedTime = formattedTime,
        weatherCode = weatherCode,
        latitude = latitude,
        longitude = longitude
    )

    return weatherData
}