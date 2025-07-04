package com.example.assignment.ui.screens.weather

data class WeatherScreenUiState(
    val isLoading: Boolean = false,
    val error: String? = null,

    val currentTemperature: String? = null,
    val currentWeatherDescription: String? = null,
    val currentWindSpeed: String? = null,
    val updatedTime: String? = null,
)

