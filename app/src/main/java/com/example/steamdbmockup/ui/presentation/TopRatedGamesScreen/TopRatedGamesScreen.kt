package com.example.steamdbmockup.ui.presentation.TopRatedGamesScreen

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipescompose.presentation.components.ColumnGameList

@Composable
fun TopRatedGamesScreen(
    navController: NavController,
    viewModel: TopRatedGamesScreenViewModel = hiltViewModel()
) {
    val topRatedGames = viewModel.TopRatedGames.value
    val loading = viewModel.TopRatedGamesLoading.value
    val listState = rememberLazyListState()

    Scaffold { it ->
        ColumnGameList(
            loading = loading,
            games = topRatedGames,
            it = it,
            navController = navController,
            loadMore = { viewModel.incrementPage() },
            listState = listState
        )
    }
}