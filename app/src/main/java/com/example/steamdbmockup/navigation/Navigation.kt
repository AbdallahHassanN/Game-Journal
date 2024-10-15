package com.example.navapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.steamdbmockup.common.Constants.Count
import com.example.steamdbmockup.common.Constants.Developer_ID
import com.example.steamdbmockup.common.Constants.GAME_ID
import com.example.steamdbmockup.ui.presentation.AchievementsScreen.AchievementsScreen
import com.example.steamdbmockup.ui.presentation.DetailScreen.DetailScreen
import com.example.steamdbmockup.ui.presentation.DeveloperScreen.DeveloperScreen
import com.example.steamdbmockup.ui.presentation.MainScreen.MainScreen
import com.example.steamdbmockup.ui.presentation.SearchScreen.SearchScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = Screens.DetailScreen.route + "/{${GAME_ID}}",
            arguments = listOf(navArgument(GAME_ID) {
                type = NavType.IntType
            })
        ) {
            DetailScreen(
                id = it.arguments!!
                    .getInt(GAME_ID), navController = navController
            )
        }
        composable(
            route = Screens.DeveloperScreen.route + "/{${Developer_ID}}",
            arguments = listOf(navArgument(Developer_ID) {
                type = NavType.IntType
            })
        ) {
            DeveloperScreen(
                id = it.arguments!!
                    .getInt(Developer_ID)
            )
        }
        composable(
            route = Screens.AchievementsScreen.route + "/{${GAME_ID}}" + "/{${Count}}",
            arguments = listOf(navArgument(GAME_ID) {
                type = NavType.IntType
            }, navArgument(Count) {
                type = NavType.IntType
            })
        ) {
            AchievementsScreen(
                id = it.arguments!!
                    .getInt(GAME_ID),
                count = it.arguments!!
                    .getInt(Count)
            )
        }
    }
}