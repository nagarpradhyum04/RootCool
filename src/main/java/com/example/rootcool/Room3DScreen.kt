package com.example.rootcool

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Room3DScreen(
    onBack: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(
            onClick = onBack,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("← Back")
        }

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            // FLOOR

            drawRect(
                color = Color(0xFFD7CCC8),
                topLeft = Offset(250f, 420f),
                size = Size(600f, 280f)
            )

            // LEFT WALL

            drawRect(
                color = Color(0xFFECEFF1),
                topLeft = Offset(150f, 180f),
                size = Size(120f, 520f)
            )

            // BACK WALL

            drawRect(
                color = Color(0xFFF5F5F5),
                topLeft = Offset(250f, 180f),
                size = Size(600f, 240f)
            )

            // RIGHT WALL

            drawRect(
                color = Color(0xFFE0E0E0),
                topLeft = Offset(850f, 180f),
                size = Size(120f, 520f)
            )

            // WINDOW

            drawRect(
                color = Color(0xFF81D4FA),
                topLeft = Offset(470f, 220f),
                size = Size(
                    160f,
                    100f
                )
            )

            // DOOR

            drawRect(
                color = Color(0xFF6D4C41),
                topLeft = Offset(180f, 480f),
                size = Size(
                    60f,
                    160f
                )
            )

            // BED

            drawRect(
                color = Color(0xFFBDBDBD),
                topLeft = Offset(500f, 500f),
                size = Size(
                    220f,
                    120f
                )
            )

            // PILLOWS

            drawRect(
                color = Color.White,
                topLeft = Offset(520f, 515f),
                size = Size(
                    50f,
                    35f
                )
            )

            drawRect(
                color = Color.White,
                topLeft = Offset(590f, 515f),
                size = Size(
                    50f,
                    35f
                )
            )

            // PLANT 1

            drawCircle(
                color = Color(0xFF2E7D32),
                radius = 30f,
                center = Offset(
                    320f,
                    620f
                )
            )

            // PLANT 2

            drawCircle(
                color = Color(0xFF388E3C),
                radius = 30f,
                center = Offset(
                    780f,
                    620f
                )
            )

            // TABLE

            drawRect(
                color = Color(0xFF8D6E63),
                topLeft = Offset(
                    380f,
                    520f
                ),
                size = Size(
                    70f,
                    70f
                )
            )
        }

        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("🏠 AI Room Visualization")

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text("🪟 Windows: 2")

                Text("🌱 Plants: 2")

                Text("💡 Light: Good")

                Text("🌬 Ventilation: Moderate")

                Text("🌡 AQI Improvement: 15%")
            }
        }
    }
}