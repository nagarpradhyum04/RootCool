package com.example.rootcool

suspend fun analyzeRoomWithGemini(
    room: Room,
    environment: EnvironmentalData
): String {

    return try {

        val prompt = """
You are RootCool AI.

Room Name: ${room.roomName}
Room Type: ${room.roomType}
City: ${room.city}
Occupants: ${room.occupants}

Temperature: ${environment.temperature}°C
Humidity: ${environment.humidity}%
Outdoor AQI: ${environment.outdoorAqi}
Weather: ${environment.weatherCondition}

IMPORTANT:

Recommend ONLY the number of plants actually required.

Do NOT assign plants to every corner.

Keep output SHORT.

Return EXACTLY in this format:

OVERALL LIGHT
X

VENTILATION
X

WINDOWS
Estimated Count (0-4)
Return a number only.

PLANTS NEEDED
X

PLANT 1
Plant Name - Corner Number

PLANT 2
Plant Name - Corner Number

IMPORTANT:
Always specify EXACT corner number.
Use only:
Corner 1
Corner 2
Corner 3
Corner 4

Never write only "Corner".

TOP ISSUE
X
"""

        val result =
            GeminiTest.model.generateContent(prompt)

        result.text ?: "No response"

    } catch (e: Exception) {

        "Error: ${e.message}"
    }
}