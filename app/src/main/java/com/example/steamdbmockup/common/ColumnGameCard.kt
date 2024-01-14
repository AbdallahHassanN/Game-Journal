package com.example.recipescompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.steamdbmockup.common.Constants.DEFAULT_Game_IMAGE
import com.example.steamdbmockup.common.CustomDivider
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.network.util.loadPicture
import com.example.steamdbmockup.ui.theme.Grey1
import com.example.steamdbmockup.ui.theme.Grey2


@Composable
fun ColumnGameCard(
    game: Result,
    onClick: () -> Unit
) {
    Card(shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(Grey2),
        ) {
        Row {
            game.background_image?.let { url ->
                val image = loadPicture(
                    url = url,
                    defaultImage = DEFAULT_Game_IMAGE
                ).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "gameImage",
                        modifier = Modifier
                            .width(150.dp)
                            .height(150.dp)
                            .padding(start = 10.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Column {
                    game.name?.let { title ->
                        Text(
                            text = title,
                            modifier = Modifier
                                .wrapContentWidth(Alignment.Start)
                                .padding(5.dp),
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White
                        )
                    }

                    game.released?.let { released ->
                        Text(
                            text = game.released,
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(5.dp),
                            style = MaterialTheme.typography.labelMedium,
                            color = Grey1
                        )
                    }
                    game.metacritic?.let { rating ->
                        if (rating.toString().startsWith("0") ) {
                            Text(
                                text = "Not Rated on metaCritic",
                                modifier = Modifier
                                    .wrapContentWidth(Alignment.End)
                                    .padding(5.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = Grey1
                            )
                        } else {
                            Text(
                                text = "${rating} on metaCritic",
                                modifier = Modifier
                                    .wrapContentWidth(Alignment.End)
                                    .padding(5.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = Grey1
                            )
                        }
                    }
                    game.parent_platforms?.let {
                        for (platform in it) {
                            Text(
                                text = platform.platform.name,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .padding(2.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = Grey1
                            )
                        }
                    }
                }
            }
        CustomDivider()
    }
}
