package com.example.rootcool

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun GeminiScreen() {

    var response by remember {
        mutableStateOf("Press Test Gemini")
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = {

                scope.launch {

                    try {

                        val result =
                            GeminiTest.model.generateContent(
                                "Say hello from RootCool in one sentence."
                            )

                        response =
                            result.text ?: "No response"

                    } catch (e: Exception) {

                        response =
                            "Error: ${e.message}"
                    }
                }
            }
        ) {
            Text("Test Gemini")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(response)
    }
}