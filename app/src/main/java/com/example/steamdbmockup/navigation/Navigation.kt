package com.example.navapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.steamdbmockup.common.Constants.Count
import com.example.steamdbmockup.common.Constants.Developer_ID
import com.example.steamdbmockup.common.Constants.GAME_ID
import com.example.steamdbmockup.ui.presentation.AchievementsScreen.AchievementsScreen
import com.example.steamdbmockup.ui.presentation.DetailScreen.DetailScreen
import com.example.steamdbmockup.ui.presentation.DeveloperScreen.DeveloperScreen
import com.example.steamdbmockup.ui.presentation.HighlyRatedGames.HighlyRatedGamesScreen
import com.example.steamdbmockup.ui.presentation.MainScreen.MainScreen
import com.example.steamdbmockup.ui.presentation.MostAnticipatedGames.MostAnticipatedScreen
import com.example.steamdbmockup.ui.presentation.SearchScreen.SearchScreen
import com.example.steamdbmockup.ui.presentation.TrendingScreen.TrendingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen) {
        composable<Screens.MainScreen> {
            MainScreen(navController = navController)
        }
        composable(route = Screens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable<Screens.DetailScreen> {
            val args = it.toRoute<Screens.DetailScreen>()
            DetailScreen(id = args.id.toInt(),navController = navController)
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
        composable(
            route = Screens.TrendingScreen.route,
        ) {
            TrendingScreen(
                navController = navController
            )
        }
        composable(
            route = Screens.MostAnticipatedScreen.route,
        ) {
            MostAnticipatedScreen(
                navController = navController
            )
        }
        composable(
            route = Screens.HighlyRatedScreen.route,
        ) {
            HighlyRatedGamesScreen(
                navController = navController
            )
        }
    }
}