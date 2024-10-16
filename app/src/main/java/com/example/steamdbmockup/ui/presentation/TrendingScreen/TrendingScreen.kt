package com.example.steamdbmockup.ui.presentation.TrendingScreen

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipescompose.presentation.components.ColumnGameList

@Composable
fun TrendingScreen(
    navController: NavController,
    viewModel: TrendingScreenViewModel = hiltViewModel()
) {
    val trendingGames = viewModel.TrendingGames.value
    val loading = viewModel.trendingLoading.value
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