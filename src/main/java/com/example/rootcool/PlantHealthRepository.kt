package com.example.rootcool

object PlantHealthRepository {

    suspend fun analyzePlant(
        plant: Plant
    ): String {

        return try {

            val prompt = """
You are RootCool Plant Health AI.

Analyze this plant.

Return EXACTLY in this format:

HEALTH SCORE
85

STATUS
Healthy

ISSUES
Minor yellowing

RECOMMENDATIONS
Reduce watering frequency
"""

            val result =
                GeminiTest.model.generateContent(
                    prompt
                )

            result.text ?: "No response"

        } catch (e: Exception) {

            "Error: ${e.message}"
        }
    }
}