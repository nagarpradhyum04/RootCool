package com.example.rootcool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rootcool.ui.theme.RootcoolTheme
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RootcoolTheme {
                RootCoolHomeScreen()
            }
        }
    }
}

@Composable
fun RootCoolHomeScreen() {

    var currentScreen by remember {
        mutableStateOf(Screen.HOME)
    }

    when (currentScreen) {

        Screen.ANALYZE_ROOM -> {
            AnalyzeRoomScreen(
                onBack = {
                    currentScreen = Screen.HOME
                }
            )
            return
        }

        Screen.MY_ROOMS -> {
            MyRoomsScreen(
                onBack = {
                    currentScreen = Screen.HOME
                },
                onRoomClick = { room ->

                    SelectedRoomRepository.selectedRoom = room

                    currentScreen = Screen.ROOM_DETAILS
                }
            )
            return
        }
        Screen.ROOM_DETAILS -> {

            RoomDetailsScreen(
                onBack = {
                    currentScreen = Screen.MY_ROOMS
                },
                onPlantsClick = {
                    currentScreen = Screen.PLANTS
                },
                on3DClick = {
                    currentScreen = Screen.ROOM_3D
                }
            )

            return
        }
        Screen.ROOM_3D -> {

            Room3DScreen(
                onBack = {
                    currentScreen = Screen.ROOM_DETAILS
                }
            )

            return
        }
        Screen.PLANTS -> {
            PlantsScreen(
                onBack = {
                    currentScreen = Screen.ROOM_DETAILS
                },
                onPlantClick = { plant ->

                    SelectedPlantRepository.selectedPlant = plant

                    currentScreen = Screen.PLANT_DETAILS
                }
            )
            return
        }
        Screen.PLANT_DETAILS -> {
            PlantDetailsScreen(
                onBack = {
                    currentScreen = Screen.PLANTS
                }
            )
            return
        }

        else -> {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🌱 RootCool",
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                currentScreen = Screen.ANALYZE_ROOM
            }
        ) {
            Text("Analyze Room")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                currentScreen = Screen.MY_ROOMS
            }
        ) {
            Text("My Rooms")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Coming Soon
            }
        ) {
            Text("Plant Health")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Coming Soon
            }
        ) {
            Text("History")
        }
    }
}