package com.example.assignment.data

import com.example.assignment.AppConstants
import com.example.assignment.bridge.platformBridge
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.accept
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

fun getKtorHttpClient(baseUrl: String = AppConstants.BASE_URL): HttpClient {
    return HttpClient(platformBridge.httpClientEngine()) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            connectTimeoutMillis = 60_000
            socketTimeoutMillis = 60_000
            requestTimeoutMillis = 60_000
        }

        defaultRequest {
            url(baseUrl)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)

//            val bearerToken = localBearerToken
//            if (bearerToken.isNotEmpty()) {
//                header(HttpHeaders.Authorization, "Bearer $bearerToken")
//            }
        }
    }
}

data class ApiResult<T>(
    val statusIsSuccessful: Boolean = false,
    val statusDescription: String = "",
    val statusValue: Int = -1,
    val jsonObject: JsonObject = JsonObject(emptyMap()),
    val bodyAsText: String = "",
    val body: T? = null
)

suspend inline fun<reified T> safeApiCall(
    crossinline apiCall: suspend () -> HttpResponse
): ApiResult<T?> {
    try {
        val httpResponse = withContext(Dispatchers.IO) {
            apiCall()
        }

        val statusIsSuccessful = httpResponse.status.isSuccess()
        val statusValue = httpResponse.status.value
        val statusDescription = httpResponse.status.description
        val bodyAsText = httpResponse.bodyAsText()
        val body = httpResponse.body<T?>()
        val jsonObject = Json.parseToJsonElement(bodyAsText).jsonObject

        val apiResult = ApiResult<T?>(
            statusIsSuccessful = statusIsSuccessful,
            statusDescription = statusDescription,
            statusValue = statusValue,
            jsonObject = jsonObject,
            bodyAsText = bodyAsText,
            body = body
        )

        return apiResult
    } catch (e: Exception) {
        e.printStackTrace()
        val errorMessage = when (e) {
            is NoTransformationFoundException -> "There was a server error (502 Bad Gateway). Please try again later."
            is HttpRequestTimeoutException -> "Unable to reach the server. Please try again after a moment."
            is SerializationException -> "Unexpected data format received. Please try again."
            is ConnectTimeoutException -> "Server is not responding. Please try again later."
            is IOException -> "Unable to connect to the network. Please ensure your device is connected and try again."
            else -> "Something went wrong. Please try again or contact support if the issue persists."
        }
        println("SafeApiCall: errorMessage = $errorMessage")
        return ApiResult(statusDescription = errorMessage)
    }
}