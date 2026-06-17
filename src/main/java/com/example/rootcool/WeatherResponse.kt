package com.example.rootcool

data class WeatherResponse(
    val current: CurrentWeather
)

data class CurrentWeather(
    val temp_c: Double,
    val humidity: Int,
    val air_quality: AirQuality?,
    val condition: Condition
)

data class AirQuality(
    val pm2_5: Double?
)

data class Condition(
    val text: String
)