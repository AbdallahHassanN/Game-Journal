package com.example.steamdbmockup.ui.presentation.TopThisYear

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
fun TopThisYearScreen(
    navController: NavController,
    viewModel: TopThisYearViewModel = hiltViewModel()
) {
    val topThisYearGames = viewModel.topThisYearGames.value
    val loading = viewModel.topThisYearGamesLoading.value
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextTitle(
                        text = stringResource(id = R.string.top_this_year),
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
            games = topThisYearGames,
            it = it,
            navController = navController,
            loadMore = { viewModel.incrementPage() },
            listState = listState
        )
    }
}