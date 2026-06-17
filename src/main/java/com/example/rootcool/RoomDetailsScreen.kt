package com.example.rootcool

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun RoomDetailsScreen(
    onBack: () -> Unit,
    onPlantsClick: () -> Unit,
    on3DClick: () -> Unit
) {

    val room = SelectedRoomRepository.selectedRoom

    val scope = rememberCoroutineScope()

    var loading by remember {
        mutableStateOf(false)
    }
    var refreshAqi by remember {
        mutableStateOf(0)
    }

    var environment by remember {
        mutableStateOf<EnvironmentalData?>(null)
    }

    LaunchedEffect(room?.city) {

        room?.let {

            environment =
                WeatherRepository.getEnvironmentData(
                    it.city
                )
        }
    }

    val aqiAnalysis =
        remember(
            environment,
            refreshAqi
        ) {

            environment?.let {

                AirQualityCalculator.calculate(
                    outdoorAqi = it.outdoorAqi,
                    occupants = room?.occupants?.toIntOrNull() ?: 1,
                    plantsNeeded =
                        room?.recommendedPlantCount ?: 1
                )
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Button(
            onClick = onBack
        ) {
            Text("← Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = room?.roomName ?: "Unknown Room",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Type: ${room?.roomType}")

        Text("City: ${room?.city}")

        Text("Occupants: ${room?.occupants}")

        Spacer(modifier = Modifier.height(16.dp))

        aqiAnalysis?.let {

            AqiCard(
                outdoorAqi = it.outdoorAqi,
                indoorAqi = it.indoorAqi,
                afterPlantsAqi = it.afterPlantsAqi,
                improvementPercent = it.improvementPercent
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        environment?.let {

            EnvironmentCard(
                temperature = it.temperature,
                humidity = it.humidity,
                weather = it.weatherCondition
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Analysis",
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = room?.analysisResult ?: "",
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                room ?: return@Button

                scope.launch {

                    loading = true

                    room.analysisResult =
                        AnalysisRepository.generateAnalysis(room)

                    room.recommendedPlantCount =
                        GeminiParser.extractPlantCount(
                            room.analysisResult
                        )
                    refreshAqi++

                    room.plants.clear()

                    room.plants.addAll(
                        PlantRecommendationParser.extractPlants(
                            room.analysisResult
                        )
                    )
                    room.roomAnalysis =
                        RoomAnalysisParser.parse(
                            room.analysisResult
                        )

                    loading = false
                }
            }
        ) {
            Text("Generate AI Analysis")
        }

        if (loading) {

            Spacer(modifier = Modifier.height(12.dp))

            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = on3DClick
        ) {
            Text("View 2D Model")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onPlantsClick
        ) {
            Text("View Plants")
        }
    }
}