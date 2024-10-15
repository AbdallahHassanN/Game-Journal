package com.example.steamdbmockup.common

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipescompose.presentation.components.ColumnAchievementCard
import com.example.steamdbmockup.model2.achievements.AchievementsResults
import com.example.steamdbmockup.ui.theme.Grey2

@SuppressLint("SuspiciousIndentation")
@Composable
fun AchievementsView(
    achievements: List<AchievementsResults>,
    loading: Boolean,
    count: Int
) {
    if (loading) {
        CircularProgressBar()
    } else {
        Column(
            modifier = Modifier
                .background(Grey2)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextTitle(
                    text = "Trophies", modifier = Modifier
                        .padding(10.dp)
                        .weight(0.8F),
                    fontSize = 20, color = Color.White
                )
                TextTitle(
                    text = "$count", modifier = Modifier
                        .padding(10.dp),
                    fontSize = 20, color = Color.White
                )
            }
            CustomDivider()
            LazyColumn(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            ) {
                itemsIndexed(
                    items = achievements
                ) { _, achievement ->
                    ColumnAchievementCard(achievement = achievement)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DisplayAchievements() {
    AchievementsView(
        achievements = listOf(
            AchievementsResults(
                name = "Test",
                description = "Test",
                id = 1,
                image = "Test",
                percent = "Test"
            )
        ),
        loading = false,
        count = 3
    )
}
