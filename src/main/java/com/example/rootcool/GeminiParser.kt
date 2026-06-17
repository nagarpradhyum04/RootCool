package com.example.rootcool

object GeminiParser {

    fun extractPlantCount(
        analysis: String
    ): Int {

        val lines = analysis.lines()

        for (i in lines.indices) {

            if (
                lines[i]
                    .trim()
                    .equals(
                        "PLANTS NEEDED",
                        ignoreCase = true
                    )
            ) {

                for (j in i + 1 until lines.size) {

                    val value =
                        lines[j].trim()

                    if (value.isNotEmpty()) {

                        return value
                            .toIntOrNull()
                            ?: 1
                    }
                }
            }
        }

        return 1
    }
}