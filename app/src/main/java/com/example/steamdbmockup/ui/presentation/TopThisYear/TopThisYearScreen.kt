package com.example.steamdbmockup.ui.presentation.TopThisYear

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
fun TopThisYearScreen(
    navController: NavController,
    viewModel: TopThisYearViewModel = hiltViewModel()
) {
    val topThisYearGames = viewModel.topThisYearGames.value
    val loading = viewModel.topThisYearGamesLoading.value
    val listState = rememberLazyListState()

    Scaffold { it ->
        ColumnGameList(
            loading = loading,
            games = topThisYearGames,
            it = it,
            navController = navController,
            loadMore = { viewModel.incrementPage() },
            listState = listState
        )
    }
}