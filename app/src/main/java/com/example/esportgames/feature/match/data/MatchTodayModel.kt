package com.example.esportgames.feature.match.data

data class MatchTodayModel(
    val gameTitle: String,
    val matchTime: String,
    val matchType: String,
    val entryFee: String,
    val prizePool: String,
    val slots: String,
    val bannerResId: Int
)
