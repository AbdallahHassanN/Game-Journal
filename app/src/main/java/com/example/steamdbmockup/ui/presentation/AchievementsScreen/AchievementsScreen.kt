package com.example.steamdbmockup.ui.presentation.AchievementsScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.steamdbmockup.common.AchievementsView
import com.example.steamdbmockup.ui.theme.Grey2

@Composable
fun AchievementsScreen(
    id: Int,
    count: Int,
    viewModel: AchievementsScreenViewModel = hiltViewModel()
) {

    val loading = viewModel.loading.value
    val achievements = viewModel.achievements.value

    LaunchedEffect(key1 = id, block = {
        viewModel.getGameAchievements(id)
    })
    Column(
        modifier = Modifier
            .background(Grey2)
            .fillMaxHeight()
    ) {
        if (achievements != null) {
            AchievementsView(
                achievements = achievements.results,
                count = count,
                loading = loading
            )
        }
    }
}


