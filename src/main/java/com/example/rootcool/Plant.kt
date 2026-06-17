package com.example.rootcool

data class Plant(
    val id: Long = System.currentTimeMillis(),

    val name: String,

    val location: String,

    var healthScore: Int = 100,

    var status: String = "",

    var issues: String = "",

    var recommendations: String = "",

    var lastAnalysis: String = "",

    var latestPhotoUri: String = ""
)