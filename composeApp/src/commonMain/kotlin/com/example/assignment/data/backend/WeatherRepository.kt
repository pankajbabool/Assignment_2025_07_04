package com.example.assignment.data.backend

import com.example.assignment.data.ApiResult
import com.example.assignment.data.model.WeatherApiResponse
import com.example.assignment.data.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class WeatherRepository(
    private val client: HttpClient,
) {

    suspend fun getWeatherInfo(
        latitude: Double,
        longitude: Double,
    ): ApiResult<WeatherApiResponse?> {

        val apiEndPoint = "/v1/forecast" +
                "?latitude=$latitude" +
                "&longitude=$longitude" +
                "&current=temperature_2m,wind_speed_10m,weather_code"
//                "&forecast_days=1"

         return safeApiCall<WeatherApiResponse?>(
            apiCall = {
                client.get(apiEndPoint)
            }
        )
    }
}