package com.example.steamdbmockup.ui.presentation.DetailScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.navapp.Screens
import com.example.steamdbmockup.R
import com.example.steamdbmockup.common.CircularProgressBar
import com.example.steamdbmockup.common.GameView
import com.example.steamdbmockup.common.RowGameCard
import com.example.steamdbmockup.ui.theme.Grey2


@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val getGameLoading = viewModel.getGameLoading.value
    val getRelatedGamesLoading = viewModel.getRelatedGamesLoading.value
    val game = viewModel.currentGame.value
    val seriesGames = viewModel.relatedGames.value
    val gameScreenshots = viewModel.currentGameScreenshots.value

    LaunchedEffect(key1 = id, block = {
        viewModel.getGameById(id)
        viewModel.getRelatedGames(id)
        viewModel.getGameScreenshots(id)
    })

    LazyColumn(
        modifier = Modifier
            .background(Grey2)
    ) {
        if (game != null) {
            item {
                GameView(game = game, loading = getGameLoading, gameScreenshots = gameScreenshots)
            }
            if (seriesGames.isNotEmpty()) {
                item {
                    Row {
                        Text(
                            text = stringResource(id = R.string.related_games),
                            modifier = Modifier
                                .wrapContentWidth(Alignment.Start)
                                .padding(10.dp),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                    }
                    LazyRow {
                        if (getRelatedGamesLoading) {
                            item { CircularProgressBar() }
                        } else {
                            itemsIndexed(
                                items = seriesGames
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
    }
    CircularProgressBar(isDisplayed = getGameLoading)
}


