package com.example.assignment.data.backend

import com.example.assignment.data.backend.local.LocalSource
import com.example.assignment.data.backend.remote.RemoteSource
import com.example.assignment.data.model.WeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WeatherRepository(
    val localSource: LocalSource,
    val remoteSource: RemoteSource,
) {

    private val _weatherDataFlow = MutableStateFlow<Result<WeatherData?>?>(null)
    val weatherDataFlow = _weatherDataFlow.asStateFlow()

    suspend fun getWeatherInfo(
        latitude: Double,
        longitude: Double,
    ) {
        val dataFromLocal = localSource.getWeatherInfo(
            latitude = latitude,
            longitude = longitude,
        )

        if (dataFromLocal.isSuccess) {
            _weatherDataFlow.update { Result.success(dataFromLocal.getOrNull()) }
        }

        val dataFromRemote = remoteSource.getWeatherInfo(
            latitude = latitude,
            longitude = longitude,
        )

        if (dataFromRemote.isSuccess) {
            _weatherDataFlow.update { Result.success(dataFromRemote.getOrNull()) }
            localSource.saveWeatherInfo(
                latitude = latitude,
                longitude = longitude,
                weatherData = dataFromRemote.getOrNull()
            )
        } else {
            _weatherDataFlow.update { Result.failure(dataFromRemote.exceptionOrNull()!!) }
        }
    }
}