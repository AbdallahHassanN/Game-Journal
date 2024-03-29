package com.example.steamdbmockup.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val color = when (dismissState.dismissDirection) {
        DismissDirection.StartToEnd -> Color(0xFFFF1744)
        DismissDirection.EndToStart -> Color(0xFFFF1744)
        null -> Color.Transparent
    }
    val direction = dismissState.dismissDirection

    Row(
        modifier = Modifier.run {
            fillMaxSize()
                .background(color)
                .padding(12.dp, 8.dp)
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (direction == DismissDirection.StartToEnd || direction == DismissDirection.EndToStart)
            Icon(
            Icons.Default.Delete,
            contentDescription = "delete"
        )
    }
}