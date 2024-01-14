package com.example.steamdbmockup.ui.presentation.MainScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.steamdbmockup.common.CircularProgressBar
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.common.RowGameList
import com.example.steamdbmockup.common.TopBar
import com.example.steamdbmockup.ui.theme.Grey2

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val Trendinggames = viewModel.TrendingGames.value
    val MostAnticipatedgames = viewModel.MostAnticipatedGames.value
    val HighRatedgames = viewModel.HighRatedGames.value
    val loading = viewModel.loading.value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey2)
    ) {
        TopBar(navController)
        Text(
            text = "Trending Games",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(10.dp)
        )
        Row {
            RowGameList(
                gameList = Trendinggames,
                navController = navController, loading = loading
            )
            CircularProgressBar(isDisplayed = loading)

            Log.d(TAG, "Main $Trendinggames")
        }
        Row {
            Text(
                text = "Most Anticipated Games",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Row {
            RowGameList(
                loading = loading, gameList = MostAnticipatedgames,
                navController = navController
            )
            CircularProgressBar(isDisplayed = loading)

        }
        Row {
            Text(
                text = "Highly Rated Games",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Row {
            RowGameList(
                loading = loading,
                gameList = HighRatedgames, navController = navController
            )
            CircularProgressBar(isDisplayed = loading)
        }
    }
}