package com.example.steamdbmockup.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.navapp.Screens
import com.example.steamdbmockup.model.Result


@Composable
fun RowGameList(
    loading:Boolean,
    gameList: List<Result>,
    navController: NavController,
){

    Box {
        LazyRow(
            modifier = Modifier
                .height(200.dp)
                .padding(start = 10.dp)
        ) {
            itemsIndexed(
                items = gameList
            ){ index,game ->
                RowGameCard(game = game, onClick = {
                    navController.navigate(Screens.DetailScreen.withArgs(game.id))
                })
            }
            /*for (game in gameList) {
                RowGameCard(game = game, onClick = {
                    navController.navigate(Screens.DetailScreen.withArgs(game.id))
                })
            }*/
        }
    }
}

