package com.example.steamdbmockup.model2.screenshots

data class Screenshots(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<gameScreenshots>
)
data class gameScreenshots(
    val height: Int,
    val id: Int,
    val image: String,
    val is_deleted: Boolean,
    val width: Int
)