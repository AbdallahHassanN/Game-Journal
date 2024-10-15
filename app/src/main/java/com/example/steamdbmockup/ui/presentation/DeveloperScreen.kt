package com.example.steamdbmockup.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.steamdbmockup.common.DeveloperView
import com.example.steamdbmockup.ui.theme.Grey2

@Composable
fun DeveloperScreen(
    id: Int,
    viewModel: DevelopersScreenViewModel = hiltViewModel()
) {

    val loading = viewModel.loading.value
    val developer = viewModel.developerInfo.value

    LaunchedEffect(key1 = id, block = {
        viewModel.getDevelopersInfo(id)
    })

    LazyColumn(
        modifier = Modifier
            .background(Grey2)
            .fillMaxHeight()
    ) {
        if (developer != null) {
            item { DeveloperView(developer = developer, loading = loading) }
        }
    }
}