package com.example.steamdbmockup.ui.presentation.HighlyRatedGames

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipescompose.presentation.components.ColumnGameList

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HighlyRatedGamesScreen(
    navController: NavController,
    viewModel: HighlyRatedScreenViewModel = hiltViewModel()
) {
    val highlyRatedGames = viewModel.highlyRatedGames.value
    val loading = viewModel.highlyRatedGamesLoading.value
    val listState = rememberLazyListState()

    Scaffold { it ->
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