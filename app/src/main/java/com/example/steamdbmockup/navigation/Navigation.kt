package com.example.navapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.steamdbmockup.ui.presentation.AchievementsScreen.AchievementsScreen
import com.example.steamdbmockup.ui.presentation.DetailScreen.DetailScreen
import com.example.steamdbmockup.ui.presentation.DeveloperScreen.DeveloperScreen
import com.example.steamdbmockup.ui.presentation.HighlyRatedGames.HighlyRatedGamesScreen
import com.example.steamdbmockup.ui.presentation.MainScreen.MainScreen
import com.example.steamdbmockup.ui.presentation.MostAnticipatedGames.MostAnticipatedScreen
import com.example.steamdbmockup.ui.presentation.SearchScreen.SearchScreen
import com.example.steamdbmockup.ui.presentation.TopRatedGamesScreen.TopRatedGamesScreen
import com.example.steamdbmockup.ui.presentation.TrendingScreen.TrendingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen
    ) {
        composable<Screens.MainScreen> {
            MainScreen(navController = navController)
        }
        composable<Screens.SearchScreen> {
            SearchScreen(navController = navController)
        }
        composable<Screens.DetailScreen> {
            val args = it.toRoute<Screens.DetailScreen>()
            DetailScreen(id = args.id.toInt(), navController = navController)
        }
        composable<Screens.DeveloperScreen> {
            val args = it.toRoute<Screens.DeveloperScreen>()
            DeveloperScreen(id = args.id.toInt())
        }
        composable<Screens.AchievementsScreen> {
            val args = it.toRoute<Screens.AchievementsScreen>()
            AchievementsScreen(id = args.id.toInt(), count = args.count)
        }
        composable<Screens.TrendingScreen> {
            TrendingScreen(navController = navController)
        }
        composable<Screens.MostAnticipatedScreen> {
            MostAnticipatedScreen(navController = navController)
        }
        composable<Screens.HighlyRatedScreen> {
            HighlyRatedGamesScreen(navController = navController)
        }
        composable<Screens.TopRatedGamesScreen> {
            TopRatedGamesScreen(navController = navController)
        }
        composable<Screens.TopThisYear> {
//            TopRatedGamesScreen(navController = navController)
        }
    }
}