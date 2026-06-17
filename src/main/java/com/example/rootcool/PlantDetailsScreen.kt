package com.example.rootcool

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun PlantDetailsScreen(
    onBack: () -> Unit
) {

    val plant = SelectedPlantRepository.selectedPlant

    val scope = rememberCoroutineScope()

    var loading by remember {
        mutableStateOf(false)
    }

    var photoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->

            photoUri = uri

            plant?.latestPhotoUri =
                uri?.toString() ?: ""
        }

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
            text = plant?.name ?: "Unknown Plant",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Location: ${plant?.location}"
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Health Score: ${plant?.healthScore}%"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                launcher.launch("image/*")
            }
        ) {
            Text("Upload Weekly Photo")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text =
                if (photoUri != null)
                    "✅ Photo Uploaded"
                else
                    "No Photo Selected"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                plant ?: return@Button

                scope.launch {

                    loading = true

                    plant.lastAnalysis =
                        PlantHealthRepository.analyzePlant(plant)
                    val report =
                        PlantHealthParser.parse(
                            plant.lastAnalysis
                        )

                    plant.healthScore =
                        report.healthScore

                    plant.status =
                        report.status

                    plant.issues =
                        report.issues

                    plant.recommendations =
                        report.recommendations

                    loading = false
                }
            }
        ) {
            Text("Analyze Plant Health")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (loading) {
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Health Score: ${plant?.healthScore}%"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Status: ${plant?.status}"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Issues: ${plant?.issues}"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Recommendations: ${plant?.recommendations}"
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = plant?.lastAnalysis ?: "No Analysis"
        )
    }
}