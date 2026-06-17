package com.example.rootcool

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
fun AnalyzeRoomScreen(
    onBack: () -> Unit
) {

    var showResult by remember {
        mutableStateOf(false)
    }

    var status by remember { mutableStateOf("") }
    if (showResult) {
        ResultScreen(status)
        return
    }
    var isAnalyzing by remember { mutableStateOf(false) }

    var image1Uri by remember { mutableStateOf<Uri?>(null) }
    var image2Uri by remember { mutableStateOf<Uri?>(null) }
    var image3Uri by remember { mutableStateOf<Uri?>(null) }
    var image4Uri by remember { mutableStateOf<Uri?>(null) }

    var selectedCorner by remember { mutableStateOf(1) }

    var roomName by remember { mutableStateOf("") }
    var roomType by remember { mutableStateOf("") }

    var city by remember { mutableStateOf("") }
    var occupants by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->

            when (selectedCorner) {
                1 -> image1Uri = uri
                2 -> image2Uri = uri
                3 -> image3Uri = uri
                4 -> image4Uri = uri
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "🌱 Analyze Your Room",
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBack
        ) {
            Text("← Back")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            UploadCard(
                title = "Corner 1",
                imageUri = image1Uri,
                onClick = {
                    selectedCorner = 1
                    launcher.launch("image/*")
                }
            )

            UploadCard(
                title = "Corner 2",
                imageUri = image2Uri,
                onClick = {
                    selectedCorner = 2
                    launcher.launch("image/*")
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            UploadCard(
                title = "Corner 3",
                imageUri = image3Uri,
                onClick = {
                    selectedCorner = 3
                    launcher.launch("image/*")
                }
            )

            UploadCard(
                title = "Corner 4",
                imageUri = image4Uri,
                onClick = {
                    selectedCorner = 4
                    launcher.launch("image/*")
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = roomName,
            onValueChange = { roomName = it },
            label = { Text("Room Name") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = roomType,
            onValueChange = { roomType = it },
            label = { Text("Room Type") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("City") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = occupants,
            onValueChange = { occupants = it },
            label = { Text("Occupants") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (
                    image1Uri == null ||
                    image2Uri == null ||
                    image3Uri == null ||
                    image4Uri == null
                ) {
                    status = "Please upload all 4 room photos."
                }
                else if (roomName.isBlank()) {
                    status = "Please enter room name."
                }

                else if (roomType.isBlank()) {
                    status = "Please enter room type."
                }

                else if (city.isBlank()) {
                    status = "Please enter city."
                }

                else if (occupants.isBlank()) {
                    status = "Please enter occupants."
                }

                else {

                    scope.launch {

                        isAnalyzing = true

                        val room = Room(
                            roomName = roomName,
                            roomType = roomType,
                            city = city,
                            occupants = occupants,

                            image1Uri = image1Uri.toString(),
                            image2Uri = image2Uri.toString(),
                            image3Uri = image3Uri.toString(),
                            image4Uri = image4Uri.toString()
                        )

                        RoomRepository.rooms.add(room)

                        status =
                            "${room.roomName} saved successfully."

                        isAnalyzing = false
                    }
                }
            }
        ) {
            Text("Analyze Room")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isAnalyzing) {
            CircularProgressIndicator()
        } else {
            Text(text = status)
        }
    }
}
