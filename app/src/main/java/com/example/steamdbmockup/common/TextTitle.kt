package com.example.steamdbmockup.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(
    text: String,
    modifier: Modifier,
    fontSize: Int,
    color: Color
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize.sp
    )
}