package com.example.steamdbmockup.ui.presentation.MainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.navapp.Screens
import com.example.steamdbmockup.R
import com.example.steamdbmockup.common.CircularProgressBar
import com.example.steamdbmockup.common.RowGameCard
import com.example.steamdbmockup.common.TextTitle
import com.example.steamdbmockup.common.TopBar
import com.example.steamdbmockup.ui.theme.Blue500
import com.example.steamdbmockup.ui.theme.Grey2

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val trendingGames = viewModel.TrendingGames.value
    val mostAnticipatedGames = viewModel.MostAnticipatedGames.value
    val highRatedGames = viewModel.HighRatedGames.value
    val trendingLoading = viewModel.trendingLoading.value
    val mostAnticipatedLoading = viewModel.mostAnticipatedLoading.value
    val highRatedLoading = viewModel.highRatedLoading.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey2)
            .padding(bottom = 20.dp)
    ) {
        item {
            TopBar(navController)
        }
        item {
            Row {
                TextTitle(
                    text = stringResource(id = R.string.trending_games),
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(0.9F),
                    fontSize = 20,
                    color = Color.White
                )
                TextTitle(
                    text = stringResource(id = R.string.show_more),
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { navController.navigate(Screens.TrendingScreen.route) },
                    fontSize = 20,
                    color = Blue500
                )
            }
            LazyRow {
                if (trendingLoading) {
                    item { CircularProgressBar() }
                } else {
                    itemsIndexed(
                        items = trendingGames
                    ) { _, game ->
                        RowGameCard(game = game, onClick = {
                            navController.navigate(Screens.DetailScreen.withArgs(game.id))
                        })
                    }
                }
            }
        }
        item {
            Row {
                TextTitle(
                    text = stringResource(id = R.string.most_anticipated_games),
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(0.9F),
                    fontSize = 20,
                    color = Color.White
                )
                TextTitle(
                    text = stringResource(id = R.string.show_more),
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { navController.navigate(Screens.MostAnticipatedScreen.route) },
                    fontSize = 20,
                    color = Blue500
                )
            }
            LazyRow {
                if (mostAnticipatedLoading) {
                    item { CircularProgressBar() }
                } else {
                    itemsIndexed(
                        items = mostAnticipatedGames
                    ) { _, game ->
                        RowGameCard(game = game, onClick = {
                            navController.navigate(Screens.DetailScreen.withArgs(game.id))
                        })
                    }
                }
            }
        }
        item {
            Row {
                TextTitle(
                    text = stringResource(id = R.string.high_rated_games),
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(0.9F),
                    fontSize = 20,
                    color = Color.White
                )
                TextTitle(
                    text = stringResource(id = R.string.show_more),
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { navController.navigate(Screens.HighlyRatedScreen.route) },
                    fontSize = 20,
                    color = Blue500
                )
            }
            LazyRow {
                if (highRatedLoading) {
                    item { CircularProgressBar() }
                } else {
                    itemsIndexed(
                        items = highRatedGames
                    ) { _, game ->
                        RowGameCard(game = game, onClick = {
                            navController.navigate(Screens.DetailScreen.withArgs(game.id))
                        })
                    }
                }
            }
        }
    }
}