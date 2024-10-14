package com.example.steamdbmockup.common

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.steamdbmockup.WebsiteRow
import com.example.steamdbmockup.common.Constants.DEFAULT_Game_IMAGE
import com.example.steamdbmockup.common.Constants.IMAGE_HEIGHT
import com.example.steamdbmockup.model2.game
import com.example.steamdbmockup.model2.screenshots.gameScreenshots
import com.example.steamdbmockup.network.util.loadPicture
import com.example.steamdbmockup.ui.theme.Grey1
import com.example.steamdbmockup.ui.theme.Grey2

@SuppressLint("SuspiciousIndentation")
@Composable
fun GameView(
    game: game,
    loading: Boolean,
    gameScreenshots: List<gameScreenshots?>
) {
    Column(
        modifier = Modifier
            .background(Grey2)
    ) {
        game.background_image.let {
            val image = loadPicture(url = it, defaultImage = DEFAULT_Game_IMAGE)
                .value
            image?.let {
                Image(
                    bitmap = it.asImageBitmap(), contentDescription = "Game Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(IMAGE_HEIGHT.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Text(
                text = game.name,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(5.dp),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                softWrap = true
            )
            game.metacritic.let { metaCritic ->
                when {
                    metaCritic >= 80 -> {
                        RoundedRectWithCenteredText(
                            meta = "$metaCritic",
                            backgroundColor = Color.Green
                        )
                    }

                    metaCritic in 50..79 -> {
                        RoundedRectWithCenteredText(
                            meta = "$metaCritic",
                            backgroundColor = Color.Yellow
                        )
                    }

                    metaCritic in 1..49 -> {
                        RoundedRectWithCenteredText(
                            meta = "$metaCritic",
                            backgroundColor = Color.Red
                        )
                    }

                    else -> {
                        RoundedRectWithCenteredText(
                            meta = "TBA",
                            backgroundColor = Color.Gray
                        )
                    }
                }

            }
        }
        CustomDivider()
        LazyRow {
            item {
                for (tag in game.tags) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp),
                    ) {
                        SuggestionChip(
                            onClick = {},
                            modifier = Modifier
                                .padding(8.dp),
                            label = {
                                Text(
                                    text = tag.name,
                                )
                            },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = Grey1,
                                labelColor = Color.White
                            )
                        )
                    }
                }
            }
        }
        LazyRow(
            modifier = Modifier
                .padding(10.dp)
        ) {
            item {
                game.developers.let { dev ->
                    for (developer in dev)
                        Text(
                            text = "By ${developer.name}",
                            modifier = Modifier
                                .wrapContentWidth(Alignment.Start)
                                .padding(10.dp),
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White
                        )
                }
                Text(
                    text = "Rated: ${game.esrb_rating.name}",
                    modifier = Modifier
                        .wrapContentWidth(Alignment.End)
                        .padding(10.dp),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
            }
        }
        Row {
            Text(
                text = game.description_raw,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
                softWrap = true
            )
        }
        LazyRow {
            item {
                for (platform in game.platforms) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp),
                    ) {
                        SuggestionChip(
                            onClick = {},
                            modifier = Modifier
                                .padding(8.dp),
                            label = {
                                Text(
                                    text = platform.platform.name,
                                )
                            },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = Grey1,
                                labelColor = Color.White
                            )
                        )
                    }
                }
            }
        }
        TextTitle(
            text = "Screenshots",
            modifier = Modifier.padding(10.dp),
            fontSize = 20,
            color = Color.White
        )
        LazyRow(
            modifier = Modifier
                .padding(10.dp)
        ) {
            if (loading) {
                item { CircularProgressBar() }
            } else {
                itemsIndexed(
                    items = gameScreenshots
                ) { _, screenshots ->
                    RowScreenshot(screenshots = screenshots)
                }
            }
        }
        WebsiteRow(website = game.website)
    }
}