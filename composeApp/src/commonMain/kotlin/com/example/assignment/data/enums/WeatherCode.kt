package com.example.assignment.data.enums

import assignment.composeapp.generated.resources.Res
import assignment.composeapp.generated.resources.png_cloud_and_lightening
import assignment.composeapp.generated.resources.png_cloud_and_sunny
import assignment.composeapp.generated.resources.png_cloud_light
import assignment.composeapp.generated.resources.png_cloud_more
import org.jetbrains.compose.resources.DrawableResource

enum class WeatherCode(val codes: List<Int>, val description: String) {
    CLEAR_SKY(listOf(0), "Clear sky"),
    MAINLY_CLEAR(listOf(1, 2, 3), "Mainly clear, partly cloudy, and overcast"),
    FOG(listOf(45, 48), "Fog and depositing rime fog"),
    DRIZZLE(listOf(51, 53, 55), "Drizzle: Light, moderate, and dense intensity"),
    FREEZING_DRIZZLE(listOf(56, 57), "Freezing Drizzle: Light and dense intensity"),
    RAIN(listOf(61, 63, 65), "Rain: Slight, moderate and heavy intensity"),
    FREEZING_RAIN(listOf(66, 67), "Freezing Rain: Light and heavy intensity"),
    SNOWFALL(listOf(71, 73, 75), "Snow fall: Slight, moderate, and heavy intensity"),
    SNOW_GRAINS(listOf(77), "Snow grains"),
    RAIN_SHOWERS(listOf(80, 81, 82), "Rain showers: Slight, moderate, and violent"),
    SNOW_SHOWERS(listOf(85, 86), "Snow showers slight and heavy"),
    THUNDERSTORM(listOf(95), "Thunderstorm: Slight or moderate"),
    THUNDERSTORM_HAIL(listOf(96, 99), "Thunderstorm with slight and heavy hail");

    companion object {
        fun fromCode(code: Int): WeatherCode? =
            entries.firstOrNull { it.codes.contains(code) }
    }
}


enum class CloudImage(val image: DrawableResource) {
    CLOUD_AND_LIGHTENING(Res.drawable.png_cloud_and_lightening),
    CLOUD_AND_SUNNY(Res.drawable.png_cloud_and_sunny),
    CLOUD_LIGHT(Res.drawable.png_cloud_light),
    CLOUD_MORE(Res.drawable.png_cloud_more);

    companion object {
        fun fromCode(code: Int): CloudImage? = when (code) {
            0 -> CLOUD_AND_SUNNY
            in 1..3 -> CLOUD_LIGHT
            in 45..48 -> CLOUD_MORE
            in 51..67, in 80..82 -> CLOUD_MORE
            in 71..77, in 85..86 -> CLOUD_MORE
            95, 96, 99 -> CLOUD_AND_LIGHTENING
            else -> null
        }
    }
}