package com.example.rootcool

object AnalysisRepository {

    suspend fun generateAnalysis(
        room: Room
    ): String {

        val environment =
            WeatherRepository.getEnvironmentData(
                room.city
            )

        return analyzeRoomWithGemini(
            room = room,
            environment = environment
        )
    }
}