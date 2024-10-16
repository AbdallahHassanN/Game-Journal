package com.example.navapp

import kotlinx.serialization.Serializable

sealed class Screens(val route:String) {
    @Serializable
    object MainScreen
    @Serializable
    data class DetailScreen(val id:String)
    @Serializable
    object SearchScreen
    @Serializable
    data class DeveloperScreen(val id:String)
    @Serializable
    data class AchievementsScreen(val id:String,val count:Int)
    @Serializable
    object TrendingScreen
    @Serializable
    object MostAnticipatedScreen
    @Serializable
    object HighlyRatedScreen
    @Serializable
    object TopRatedGamesScreen
    @Serializable
    object TopThisYear
}