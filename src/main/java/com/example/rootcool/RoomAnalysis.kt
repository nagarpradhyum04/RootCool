package com.example.rootcool

data class RoomAnalysis(

    val overallLight: String = "",

    val ventilation: String = "",

    val windows: Int = 0,

    val topIssue: String = "",

    val corners: MutableList<CornerAnalysis> =
        mutableListOf()
)