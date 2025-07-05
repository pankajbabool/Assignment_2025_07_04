package com.example.assignment.ui.screens.weather

import com.example.assignment.data.enums.CloudImage

data class WeatherScreenUiState(
    val isLoading: Boolean = false,
    val error: String? = null,

    val currentTemperature: String? = null,
    val currentWeatherDescription: String? = null,
    val currentWindSpeed: String? = null,
    val updatedTime: String? = null,

    val cloudImage: CloudImage? = null
)

