package com.example.rootcool

object PlantHealthParser {

    fun parse(
        analysis: String
    ): PlantHealthReport {

        var score = 80
        var status = ""
        var issues = ""
        var recommendations = ""

        val lines = analysis.lines()

        for (i in lines.indices) {

            when (
                lines[i].trim()
            ) {

                "HEALTH SCORE" -> {
                    score =
                        lines.getOrNull(i + 1)
                            ?.trim()
                            ?.replace("%", "")
                            ?.toIntOrNull()
                            ?: 80
                }

                "STATUS" -> {
                    status =
                        lines.getOrNull(i + 1)
                            ?.trim()
                            ?: ""
                }

                "ISSUES" -> {
                    issues =
                        lines.getOrNull(i + 1)
                            ?.trim()
                            ?: ""
                }

                "RECOMMENDATIONS" -> {
                    recommendations =
                        lines.getOrNull(i + 1)
                            ?.trim()
                            ?: ""
                }
            }
        }

        return PlantHealthReport(
            score,
            status,
            issues,
            recommendations
        )
    }
}