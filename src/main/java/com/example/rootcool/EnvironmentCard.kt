package com.example.rootcool

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EnvironmentCard(
    temperature: Double,
    humidity: Int,
    weather: String
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "☀ Environment",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Temperature: $temperature°C")

            Text("Humidity: $humidity%")

            Text("Weather: $weather")
        }
    }
}