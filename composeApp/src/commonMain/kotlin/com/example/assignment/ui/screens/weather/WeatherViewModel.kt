package com.example.assignment.ui.screens.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.backend.WeatherRepository
import com.example.assignment.data.backend.local.LocalSource
import com.example.assignment.data.backend.remote.RemoteSource
import com.example.assignment.data.enums.CloudImage
import com.example.assignment.data.getKtorHttpClient
import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.mobile
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel (
    val repository: WeatherRepository = WeatherRepository(
        localSource = LocalSource(),
        remoteSource = RemoteSource(
            httpClient = getKtorHttpClient()
        )
    )
): ViewModel() {

    private val _uiState = MutableStateFlow(WeatherScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        collectFlows()
        getLastSaved()
    }

    fun onEvent(event: WeatherScreenEvents) {
        when (event) {
            is WeatherScreenEvents.DismissAlertDialog -> dismissError()
            is WeatherScreenEvents.GetCurrentLocation -> getCurrentLocation()
            is WeatherScreenEvents.GetWeatherInfo -> getWeatherInfo(event.latitude, event.longitude)
        }
    }

    private fun dismissError() {
        _uiState.update { it.copy(error = null) }
    }

    private fun collectFlows() {
        viewModelScope.launch {
            repository.weatherDataFlow.collectLatest { result ->
                result
                    ?.onSuccess { data ->
                        _uiState.update { it.copy(error = null) }
                        if (data != null) {
                            val currentTemperature = data.currentTemperature
                            val currentWeatherDescription = data.currentWeatherDescription
                            val currentWindSpeed = data.currentWindSpeed
                            val updatedTime = data.updatedTime

                            val cloudImage = CloudImage.fromCode(data.weatherCode)
                            _uiState.update {
                                it.copy(
                                    currentTemperature = currentTemperature,
                                    currentWeatherDescription = currentWeatherDescription,
                                    currentWindSpeed = currentWindSpeed,
                                    updatedTime = updatedTime,
                                    cloudImage = cloudImage
                                )
                            }
                        }
                    }
                    ?.onFailure { throwable ->
                        _uiState.update { it.copy(error = throwable.message) }
                    }
            }
        }
    }

    private fun getLastSaved() {
        viewModelScope.launch {
            val lastSaved = repository.getLastSaved()
            if (lastSaved != null && lastSaved.contains("_")) {
                val (latitude, longitude) = lastSaved.split("_").map { it.toDoubleOrNull() }
                if (latitude != null && longitude != null) {
                    _uiState.update { it.copy(latitude = latitude, longitude = longitude) }
                    getWeatherInfo(latitude = latitude, longitude = longitude)
                }
            }
        }
    }

    private fun getWeatherInfo(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(500L)
            repository.getWeatherInfo(latitude = latitude, longitude = longitude)
            _uiState.update { it.copy(latitude = latitude, longitude = longitude) }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun getCurrentLocation() {
        viewModelScope.launch {
            Geolocator.mobile()
                .current()
                .onFailed { throwable -> println("LOCATION ERROR: ${throwable.message}") }
                .onSuccess { location ->
                    val latitude = location.coordinates.latitude
                    val longitude = location.coordinates.longitude
                    println("LOCATION: Latitude: $latitude, Longitude: $longitude")
                    _uiState.update { it.copy(latitude = latitude, longitude = longitude) }

                    getWeatherInfo(latitude = latitude, longitude = longitude)
                }
        }
    }
}