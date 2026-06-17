package com.example.rootcool

object AirQualityCalculator {

    fun calculate(
        outdoorAqi: Int,
        occupants: Int,
        plantsNeeded: Int
    ): AirQualityAnalysis {

        val indoorAqi =
            (outdoorAqi * 0.8).toInt() +
                    (occupants * 2)

        val improvementPercent =
            when {

                plantsNeeded <= 1 -> 8

                plantsNeeded == 2 -> 15

                plantsNeeded == 3 -> 21

                plantsNeeded == 4 -> 26

                else -> 30
            }

        val afterPlantsAqi =
            (indoorAqi *
                    (100 - improvementPercent) / 100.0)
                .toInt()

        return AirQualityAnalysis(
            outdoorAqi = outdoorAqi,
            indoorAqi = indoorAqi,
            afterPlantsAqi = afterPlantsAqi,
            improvementPercent = improvementPercent
        )
    }
}