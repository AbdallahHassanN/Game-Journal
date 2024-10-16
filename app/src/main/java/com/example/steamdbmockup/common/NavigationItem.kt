package com.example.steamdbmockup.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.navapp.Screens

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val navigationItems = listOf(
    NavigationItem(
        title = "Top 250 Games",
        icon = Icons.Default.Favorite,
        route = Screens.TopRatedGamesScreen.toString().trim()
    ),
    NavigationItem(
        title = "Popular this year",
        icon = Icons.Default.Favorite,
        route = Screens.TopThisYear.toString()
    )
)