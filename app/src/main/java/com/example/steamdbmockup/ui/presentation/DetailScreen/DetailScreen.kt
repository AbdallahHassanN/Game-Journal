package com.example.steamdbmockup.ui.presentation.DetailScreen


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.steamdbmockup.common.CircularProgressBar
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.common.RowGameList
import com.example.steamdbmockup.common.GameView
import com.example.steamdbmockup.ui.theme.Grey2


@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val loading = viewModel.loading.value
    val game = viewModel.CurrentGame.value
    val seriesGames = viewModel.RelatedGames.value

    if (id != null) {

        LaunchedEffect(key1 = id, block = {
            viewModel.getGameById(id)
            viewModel.getRelatedGames(id)
        })

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Grey2)
                .verticalScroll(rememberScrollState())
        ) {
            if (game != null) {
                Column {
                    GameView(game = game, loading = loading)
                }
                if (seriesGames.isNotEmpty()) {
                    Row {
                        Log.d(TAG, "Series = $seriesGames")
                        Text(
                            text = "Related Games",
                            modifier = Modifier
                                .wrapContentWidth(Alignment.Start)
                                .padding(10.dp),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        RowGameList(
                            loading = loading, gameList = seriesGames,
                            navController = navController
                        )
                    }
                }
            } else {
                Log.d(TAG, "Not Found")
            }
        }
        CircularProgressBar(isDisplayed = loading)
    }
}


