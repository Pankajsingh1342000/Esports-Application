package com.example.esportgames.feature.home.data

data class MatchModel(
    val bannerResId: Int,
    val time: String,
    val title: String,
    val prize: String,
    val type: String,
    val slots: String,
    val entryFee: String,
    val countdown: String,
    val organizer: String
)
