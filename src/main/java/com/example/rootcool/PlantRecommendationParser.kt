package com.example.rootcool

object PlantRecommendationParser {

    fun extractPlants(
        analysis: String
    ): MutableList<Plant> {

        val plants =
            mutableListOf<Plant>()

        val regex =
            Regex("([A-Za-z ]+)\\s*-\\s*(Corner\\s*\\d+)")

        regex.findAll(analysis)
            .forEach { match ->

                val plantName =
                    match.groupValues[1].trim()

                val location =
                    match.groupValues[2].trim()

                plants.add(
                    Plant(
                        name = plantName,
                        location = location
                    )
                )
            }

        return plants
    }
}