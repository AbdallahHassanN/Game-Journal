package com.example.steamdbmockup.ui.presentation.MostAnticipatedGames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostAnticipatedScreen(
    navController: NavController,
    viewModel: MostAnticipatedScreenViewModel = hiltViewModel()
) {

    val trendingGames = viewModel.mostAnticipatedGames.value
    val loading = viewModel.mostAnticipatedGamesLoading.value
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextTitle(
                        text = stringResource(id = R.string.most_anticipated_games),
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
            games = trendingGames,
            it = it,
            navController = navController,
            loadMore = { viewModel.incrementPage() },
            listState = listState
        )
    }
}