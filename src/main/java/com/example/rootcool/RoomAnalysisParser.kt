package com.example.rootcool

object RoomAnalysisParser {

    fun parse(
        analysis: String
    ): RoomAnalysis {

        return RoomAnalysis(

            overallLight =
                extractSection(
                    analysis,
                    "OVERALL LIGHT"
                ),

            ventilation =
                extractSection(
                    analysis,
                    "VENTILATION"
                ),

            windows =
                extractSection(
                    analysis,
                    "WINDOWS"
                ).toIntOrNull() ?: 0,

            topIssue =
                extractSection(
                    analysis,
                    "TOP ISSUE"
                )
        )
    }

    private fun extractSection(
        text: String,
        label: String
    ): String {

        val lines = text.lines()

        for (i in lines.indices) {

            if (
                lines[i]
                    .trim()
                    .equals(label, true)
            ) {

                for (j in i + 1 until lines.size) {

                    val value =
                        lines[j].trim()

                    if (value.isNotEmpty()) {
                        return value
                    }
                }
            }
        }

        return ""
    }
}