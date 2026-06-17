package com.example.rootcool

object WeatherRepository {

    private fun pm25ToAqi(
        pm25: Double
    ): Int {

        return when {

            pm25 <= 12.0 -> 50

            pm25 <= 35.4 -> 100

            pm25 <= 55.4 -> 150

            pm25 <= 150.4 -> 200

            pm25 <= 250.4 -> 300

            else -> 400
        }
    }

    suspend fun getEnvironmentData(
        city: String
    ): EnvironmentalData {

        return try {

            val response =
                RetrofitClient.api.getWeather(
                    apiKey = WeatherApiConfig.API_KEY,
                    city = city
                )

            val pm25 =
                response.current.air_quality?.pm2_5 ?: 0.0

            EnvironmentalData(

                temperature =
                    response.current.temp_c,

                humidity =
                    response.current.humidity,

                outdoorAqi =
                    pm25ToAqi(pm25),

                weatherCondition =
                    response.current.condition.text
            )

        } catch (e: Exception) {

            EnvironmentalData(
                temperature = 30.0,
                humidity = 50,
                outdoorAqi = 50,
                weatherCondition = "Unknown"
            )
        }
    }
}