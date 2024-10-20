package com.example.steamdbmockup.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.navapp.Screens

data class NavigationItem<T:Any>(
    val title: String,
    val icon: ImageVector,
    val route: T
)

val navigationItems = listOf(
    NavigationItem(
        title = "Top 250 Games",
        icon = Icons.Default.Favorite,
        route = Screens.TopRatedGamesScreen
    ),
    NavigationItem(
        title = "Popular this year",
        icon = Icons.Default.Favorite,
        route = Screens.TopThisYear
    )
)