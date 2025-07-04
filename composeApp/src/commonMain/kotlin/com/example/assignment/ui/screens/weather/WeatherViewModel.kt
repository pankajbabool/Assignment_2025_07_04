package com.example.assignment.ui.screens.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.backend.WeatherCode
import com.example.assignment.data.backend.WeatherRepository
import com.example.assignment.data.getKtorHttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class WeatherViewModel (
    val repository: WeatherRepository = WeatherRepository(getKtorHttpClient())
): ViewModel() {

    private val _uiState = MutableStateFlow(WeatherScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: WeatherScreenEvents) {
        when (event) {
            is WeatherScreenEvents.DismissAlertDialog -> dismissError()
            is WeatherScreenEvents.GetWeatherInfo -> getWeatherInfo(event.latitude, event.longitude)
        }
    }

    private fun dismissError() {
        _uiState.update { it.copy(error = null) }
    }

    private fun getWeatherInfo(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.getWeatherInfo(
                latitude = latitude,
                longitude = longitude
            )
            _uiState.update { it.copy(isLoading = false) }

            val body = result.body
            if (result.statusIsSuccessful && body != null) {
                val currentTemperature = body.current.temperature_2m
                val currentTemperatureUnits = body.current_units.temperature_2m
                val currentTemperatureWithUnits = "$currentTemperature $currentTemperatureUnits"

                val currentWindSpeed = body.current.wind_speed_10m
                val currentWindSpeedUnits = body.current_units.wind_speed_10m
                val currentWindSpeedWithUnits = "$currentWindSpeed $currentWindSpeedUnits"


                val updatedTime = body.current.time
                val formattedTime = formatLocalDateTime(updatedTime)

                val weatherCode = body.current.weather_code
                val weatherDescription = WeatherCode.fromCode(weatherCode)?.description

                _uiState.update {
                    it.copy(
                        currentTemperature = currentTemperatureWithUnits,
                        currentWeatherDescription = weatherDescription,
                        currentWindSpeed = currentWindSpeedWithUnits,
                        updatedTime = formattedTime
                    )
                }
            } else {
                val errorMessage = body?.reason ?: result.statusDescription
                if (body?.error == true && errorMessage.isNotEmpty()) {
                    _uiState.update { it.copy(error = errorMessage) }
                }
            }
        }
    }

    //2025-07-04T16:45 -> 04 Jul, 10:15 PM
    private fun formatLocalDateTime(iso: String): String {
        val localDateTime = LocalDateTime.parse(iso)
            .toInstant(TimeZone.UTC)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        val day = localDateTime.dayOfMonth.toString().padStart(2, '0')
        val month = localDateTime.month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }

        val hour24 = localDateTime.hour
        val hour12 = (hour24 % 12).let { if (it == 0) 12 else it }
        val minute = localDateTime.minute.toString().padStart(2, '0')
        val amPm = if (hour24 < 12) "AM" else "PM"

        val formattedTime = "$day $month, $hour12:$minute $amPm"
        println("Formatted Time: $iso -> $formattedTime")

        return formattedTime
    }
}