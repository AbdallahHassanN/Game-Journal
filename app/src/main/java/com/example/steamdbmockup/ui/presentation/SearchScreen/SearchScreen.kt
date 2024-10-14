package com.example.steamdbmockup.ui.presentation.SearchScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.recipescompose.presentation.components.ColumnGameList
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.common.SearchAppBar
import com.example.steamdbmockup.common.SearchType

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val gamesByName = viewModel.gamesByName.value
    val gamesByGenre = viewModel.gamesByGenre.value

    val query by viewModel.query.collectAsStateWithLifecycle()

    val loading = viewModel.loading.value
    val listState = rememberLazyListState()

    Log.d(TAG, "Query : $query")
    var shouldScrollToTop by remember { mutableStateOf(false) }

    // Effect to scroll to the top when shouldScrollToTop is true
    LaunchedEffect(shouldScrollToTop) {
        if (shouldScrollToTop) {
            listState.scrollToItem(0)
            // Reset the flag after scrolling
            shouldScrollToTop = false
        }
    }
    Scaffold(
        topBar = {
            SearchAppBar(query = query,
                onQueryChanged = { viewModel.onQueryChanged(it) },
                onExecuteSearch = {
                    shouldScrollToTop = true
                    viewModel.search(SearchType.BY_NAME)
                },
                onChipsSearch = {
                    shouldScrollToTop = true
                    viewModel.search(SearchType.BY_GENRE)
                })
        }
    ) { it ->
        ColumnGameList(
            loading = loading,
            games = if (viewModel.currentSearchType.value == SearchType.BY_NAME) gamesByName else gamesByGenre,
            it = it,
            navController = navController,
            loadMore = { viewModel.incrementPage() },
            listState = listState
        )
    }
}