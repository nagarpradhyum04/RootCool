package com.example.rootcool

data class Room(
    val id: Long = System.currentTimeMillis(),

    val roomName: String,

    val roomType: String,

    val city: String,

    val occupants: String,

    val image1Uri: String,

    val image2Uri: String,

    val image3Uri: String,

    val image4Uri: String,

    var analysisResult: String = "",

    var modelGenerated: Boolean = false,

    var recommendedPlantCount: Int = 0,

    var roomAnalysis: RoomAnalysis = RoomAnalysis(),

    var visionAnalysis: VisionAnalysis = VisionAnalysis(),

    var plants: MutableList<Plant> = mutableListOf()
)