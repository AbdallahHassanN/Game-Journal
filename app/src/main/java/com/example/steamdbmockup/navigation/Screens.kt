package com.example.navapp

import kotlinx.serialization.Serializable

sealed class Screens(val route:String) {
    @Serializable
    object MainScreen
    @Serializable
    data class DetailScreen(val id:String)
    object SearchScreen : Screens("searchScreen")
    object DeveloperScreen : Screens("developerScreen")
    object AchievementsScreen : Screens("achievementsScreen")
    object TrendingScreen : Screens("trendingScreen")
    object MostAnticipatedScreen : Screens("mostAnticipatedScreen")
    object HighlyRatedScreen : Screens("highlyRatedScreen")

    fun withArgs(vararg args:Int):String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}