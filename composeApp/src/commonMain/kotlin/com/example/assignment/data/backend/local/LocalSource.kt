package com.example.assignment.data.backend.local

import com.example.assignment.data.local.MyPreferences
import com.example.assignment.data.model.WeatherData
import com.example.assignment.extensions.toJsonString
import com.example.assignment.extensions.toMyDataClass

class LocalSource {

    suspend fun getWeatherInfo(
        latitude: Double,
        longitude: Double,
    ): Result<WeatherData?> {
        val key = "${latitude}_${longitude}"

        val data = MyPreferences.getValue<String?>(key) as String?
        val weatherData = data?.toMyDataClass<WeatherData?>()

        if (weatherData != null) {
            return Result.success(weatherData)
        } else {
            return Result.failure(Throwable("No data found"))
        }
    }

    suspend fun saveWeatherInfo(
        latitude: Double,
        longitude: Double,
        weatherData: WeatherData?,
    ) {
        val key = "${latitude}_${longitude}"
        val data = weatherData.toJsonString().toString()

        MyPreferences.setValue<String>(value = data, key = key)
    }
}