package com.example.steamdbmockup.model2.achievements

data class Achievements(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<AchievementsResults>
)
data class AchievementsResults(
    val description: String,
    val id: Int,
    val image: String,
    val name: String,
    val percent: String
)