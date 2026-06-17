package com.example.rootcool

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AqiCard(
    outdoorAqi: Int,
    indoorAqi: Int,
    afterPlantsAqi: Int,
    improvementPercent: Int
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "🌬 Air Quality",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Outdoor AQI: $outdoorAqi")

            Text("Estimated Indoor AQI: $indoorAqi")

            Text("AQI After Plants: $afterPlantsAqi")

            Text("Improvement: $improvementPercent%")
        }
    }
}