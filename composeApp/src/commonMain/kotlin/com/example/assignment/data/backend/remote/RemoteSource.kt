package com.example.assignment.data.backend.remote

import com.example.assignment.data.model.WeatherApiResponse
import com.example.assignment.data.model.WeatherData
import com.example.assignment.data.model.toWeatherData
import com.example.assignment.data.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteSource(
    private val httpClient: HttpClient,
) {

    suspend fun getWeatherInfo(
        latitude: Double,
        longitude: Double,
    ): Result<WeatherData> {

        val apiEndPoint = "/v1/forecast" +
                "?latitude=$latitude" +
                "&longitude=$longitude" +
                "&current=temperature_2m,wind_speed_10m,weather_code"

        val result = safeApiCall<WeatherApiResponse?>(
            apiCall = {
                httpClient.get(apiEndPoint)
            }
        )

        val body = result.body
        if (result.statusIsSuccessful && body != null) {
            val weatherData = body.toWeatherData()
            return Result.success(weatherData)
        } else {
            val errorMessage = body?.reason ?: result.statusDescription
            return Result.failure(Throwable(errorMessage))
        }
    }

}