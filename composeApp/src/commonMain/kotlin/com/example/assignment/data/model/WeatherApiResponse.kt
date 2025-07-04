package com.example.assignment.data.model

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