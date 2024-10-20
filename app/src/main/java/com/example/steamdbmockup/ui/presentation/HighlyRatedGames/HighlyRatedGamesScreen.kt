package com.example.steamdbmockup.ui.presentation.HighlyRatedGames

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipescompose.presentation.components.ColumnGameList
import com.example.steamdbmockup.R
import com.example.steamdbmockup.common.TextTitle
import com.example.steamdbmockup.ui.theme.Grey2

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HighlyRatedGamesScreen(
    navController: NavController,
    viewModel: HighlyRatedScreenViewModel = hiltViewModel()
) {
    val highlyRatedGames = viewModel.highlyRatedGames.value
    val loading = viewModel.highlyRatedGamesLoading.value
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextTitle(
                        text = stringResource(id = R.string.high_rated_games),
                        modifier = Modifier
                            .padding(10.dp),
                        fontSize = 20,
                        color = Color.White
                    )
                },
                modifier = Modifier
                    .background(Grey2),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Grey2

                )
            )
        }
    ) { it ->
        ColumnGameList(
            loading = loading,
            games = highlyRatedGames,
            it = it,
            navController = navController,
            loadMore = { viewModel.incrementPage() },
            listState = listState
        )
    }
}