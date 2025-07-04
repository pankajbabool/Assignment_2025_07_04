package com.example.assignment.data.backend;

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
