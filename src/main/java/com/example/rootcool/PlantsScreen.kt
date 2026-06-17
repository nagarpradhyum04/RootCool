package com.example.rootcool

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable

@Composable
fun PlantsScreen(
    onBack: () -> Unit,
    onPlantClick: (Plant) -> Unit
){

    val room = SelectedRoomRepository.selectedRoom

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = onBack
        ) {
            Text("← Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Plants",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(room?.plants ?: emptyList()) { plant ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clickable {
                            onPlantClick(plant)
                        }
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text("🌱 ${plant.name}")

                        Text("Location: ${plant.location}")

                        Text("Health Score: ${plant.healthScore}%")
                    }
                }
            }
        }
    }
}