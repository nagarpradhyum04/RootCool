package com.example.rootcool

import com.google.ai.client.generativeai.GenerativeModel

object GeminiTest {

    const val API_KEY = "AQ.Ab8RN6LZ7iEfmLnZkCXoX77n6JzNkX4b8jCyLmfbLJsZ-Y9kDA"
    val model = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = API_KEY
    )
}