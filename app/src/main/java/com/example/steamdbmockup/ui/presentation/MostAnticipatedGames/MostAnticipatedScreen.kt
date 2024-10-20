package com.example.steamdbmockup.ui.presentation.MostAnticipatedGames

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipescompose.presentation.components.ColumnGameList

@Composable
fun MostAnticipatedScreen(
    navController: NavController,
    viewModel: MostAnticipatedScreenViewModel = hiltViewModel()
) {

    val trendingGames = viewModel.mostAnticipatedGames.value
    val loading = viewModel.mostAnticipatedGamesLoading.value
    val listState = rememberLazyListState()

    Scaffold { it ->
        ColumnGameList(
            loading = loading,
            games = trendingGames,
            it = it,
            navController = navController,
            loadMore = { viewModel.incrementPage() },
            listState = listState
        )
    }
}