package com.example.recipescompose.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.navapp.Screens
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.ui.theme.Grey2

@Composable
fun ColumnGameList(
    loading: Boolean,
    games: List<Result>?,
    it: PaddingValues,
    navController: NavController,
    loadMore: () -> Unit,// Callback to load more data
    listState: LazyListState // Add the LazyListState parameter

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Grey2)
            .padding(it)
    ) {
        LazyColumn(
            state = listState
        ) {
            itemsIndexed(
                items = games ?: emptyList()
            ) { index, game ->
                ColumnGameCard(game = game, onClick = {
                    if (game.id != null) {
                        navController.navigate(Screens.DetailScreen.withArgs(game.id))
                    } else {
                        Log.d(TAG, "Error")
                    }
                })
                if (index == games?.size?.minus(1) && !loading) {
                    loadMore()
                }
            }
        }
    }
}