package com.example.navapp

sealed class Screens(val route:String) {
    object MainScreen : Screens("mainScreen")
    object DetailScreen : Screens("detailScreen")
    object SearchScreen : Screens("searchScreen")

    fun withArgs(vararg args:Int):String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}