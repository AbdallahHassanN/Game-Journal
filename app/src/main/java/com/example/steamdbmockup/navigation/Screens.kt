package com.example.navapp

sealed class Screens(val route:String) {
    object MainScreen : Screens("mainScreen")
    object DetailScreen : Screens("detailScreen")
    object SearchScreen : Screens("searchScreen")
    object DeveloperScreen : Screens("developerScreen")
    object AchievementsScreen : Screens("achievementsScreen")
    object TrendingScreen : Screens("trendingScreen")
    object MostAnticipatedScreen : Screens("mostAnticipatedScreen")

    fun withArgs(vararg args:Int):String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}