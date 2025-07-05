package com.example.assignment.ui.screens.weather

sealed class WeatherScreenEvents {
    object DismissAlertDialog: WeatherScreenEvents()
    data class GetWeatherInfo(val latitude: Double, val longitude: Double): WeatherScreenEvents()
    object GetCurrentLocation: WeatherScreenEvents()
}