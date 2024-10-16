package com.example.steamdbmockup.ui.presentation.MainScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.navapp.Screens
import com.example.steamdbmockup.R
import com.example.steamdbmockup.common.CircularProgressBar
import com.example.steamdbmockup.common.RowGameCard
import com.example.steamdbmockup.common.TextTitle
import com.example.steamdbmockup.common.TopBar
import com.example.steamdbmockup.common.navigationItems
import com.example.steamdbmockup.ui.theme.Blue500
import com.example.steamdbmockup.ui.theme.Grey2
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val trendingGames = viewModel.TrendingGames.value
    val mostAnticipatedGames = viewModel.MostAnticipatedGames.value
    val highRatedGames = viewModel.HighRatedGames.value
    val trendingLoading = viewModel.trendingLoading.value
    val mostAnticipatedLoading = viewModel.mostAnticipatedLoading.value
    val highRatedLoading = viewModel.highRatedLoading.value

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Grey2
    ) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Grey2)
                    ) {
                        navigationItems.forEachIndexed { index, item ->
                            NavigationDrawerItem(
                                label = { Text(text = item.title, color = Color.White) },
                                icon = {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                },
                                selected = index == selectedItemIndex,
                                onClick = {
                                    Log.v(
                                        "Navigation", "Clicked ${item.route} and " +
                                                "${Screens.TopRatedGamesScreen}"
                                    )
//                                    navController.navigate(item.route)
                                    selectedItemIndex = index
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                modifier = Modifier
                                    .background(Grey2),
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Grey2,
                                    selectedContainerColor = Grey2,
                                    unselectedTextColor = Color.White,
                                    selectedTextColor = Color.White,
                                    unselectedIconColor = Color.White,
                                    selectedIconColor = Color.White
                                )
                            )
                        }
                    }
                }
            },
            drawerState = drawerState,
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            TextTitle(
                                text = stringResource(id = R.string.app_name),
                                modifier = Modifier
                                    .padding(10.dp),
                                fontSize = 20,
                                color = Color.White
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.White
                                )
                            }
                        },
                        modifier = Modifier
                            .background(Grey2),
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Grey2

                        )
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Grey2)
                        .padding(bottom = 20.dp, top = 20.dp)
                ) {
                    item {
                        TopBar(navController)
                    }
                    item {
                        Row {
                            TextTitle(
                                text = stringResource(id = R.string.trending_games),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .weight(0.9F),
                                fontSize = 20,
                                color = Color.White
                            )
                            TextTitle(
                                text = stringResource(id = R.string.show_more),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable {
                                        navController
                                            .navigate(Screens.TrendingScreen)
                                    },
                                fontSize = 20,
                                color = Blue500
                            )
                        }
                        LazyRow {
                            if (trendingLoading) {
                                item { CircularProgressBar() }
                            } else {
                                itemsIndexed(
                                    items = trendingGames
                                ) { _, game ->
                                    RowGameCard(game = game, onClick = {
                                        navController.navigate(
                                            Screens.DetailScreen(id = game.id.toString())
                                        )
                                    })
                                }
                            }
                        }
                    }
                    item {
                        Row {
                            TextTitle(
                                text = stringResource(id = R.string.most_anticipated_games),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .weight(0.9F),
                                fontSize = 20,
                                color = Color.White
                            )
                            TextTitle(
                                text = stringResource(id = R.string.show_more),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable {
                                        navController
                                            .navigate(Screens.MostAnticipatedScreen)
                                    },
                                fontSize = 20,
                                color = Blue500
                            )
                        }
                        LazyRow {
                            if (mostAnticipatedLoading) {
                                item { CircularProgressBar() }
                            } else {
                                itemsIndexed(
                                    items = mostAnticipatedGames
                                ) { _, game ->
                                    RowGameCard(game = game, onClick = {
                                        navController.navigate(
                                            Screens.DetailScreen(id = game.id.toString())
                                        )
                                    })
                                }
                            }
                        }
                    }
                    item {
                        Row {
                            TextTitle(
                                text = stringResource(id = R.string.high_rated_games),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .weight(0.9F),
                                fontSize = 20,
                                color = Color.White
                            )
                            TextTitle(
                                text = stringResource(id = R.string.show_more),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable {
                                        navController
                                            .navigate(Screens.HighlyRatedScreen)
                                    },
                                fontSize = 20,
                                color = Blue500
                            )
                        }
                        LazyRow {
                            if (highRatedLoading) {
                                item { CircularProgressBar() }
                            } else {
                                itemsIndexed(
                                    items = highRatedGames
                                ) { _, game ->
                                    RowGameCard(game = game, onClick = {
                                        navController.navigate(
                                            Screens.DetailScreen(id = game.id.toString())
                                        )
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}