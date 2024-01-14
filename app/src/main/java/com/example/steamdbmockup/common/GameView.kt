package com.example.steamdbmockup.common

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration.Companion.LineThrough
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.navapp.Screens
import com.example.steamdbmockup.R
import com.example.steamdbmockup.WebsiteRow
import com.example.steamdbmockup.common.Constants.DEFAULT_Game_IMAGE
import com.example.steamdbmockup.common.Constants.IMAGE_HEIGHT
import com.example.steamdbmockup.common.Constants.TAG
import com.example.steamdbmockup.model.Result
import com.example.steamdbmockup.model.results
import com.example.steamdbmockup.model2.game
import com.example.steamdbmockup.network.response.GameResponse
import com.example.steamdbmockup.network.response.SingleGameResponse
import com.example.steamdbmockup.network.util.loadPicture
import com.example.steamdbmockup.ui.theme.Blue500
import com.example.steamdbmockup.ui.theme.Grey1
import com.example.steamdbmockup.ui.theme.Grey2

@SuppressLint("SuspiciousIndentation")
@Composable
fun GameView(
    game: game,
    loading: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey2)
    ) {
        game.background_image?.let {
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
            game.name?.let { title ->
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(5.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    softWrap = true
                )
            }
            game.metacritic?.let { metaCritic ->
                if (metaCritic >= 80) {
                    RoundedRectWithCenteredText(
                        meta = "$metaCritic",
                        backgroundColor = Color.Green
                    )
                } else if (metaCritic in 50..79) {
                    RoundedRectWithCenteredText(
                        meta = "$metaCritic",
                        backgroundColor = Color.Yellow
                    )
                } else if (metaCritic in 1..49) {
                    RoundedRectWithCenteredText(
                        meta = "$metaCritic",
                        backgroundColor = Color.Red
                    )
                } else {
                    RoundedRectWithCenteredText(meta = "TBA", backgroundColor = Color.Gray)
                }
            }
        }
        CustomDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
        ) {
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            game.developers?.let { dev ->
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
            game.esrb_rating?.let { esrbR ->
                Text(
                    text = "Rated: ${esrbR.name}",
                    modifier = Modifier
                        .wrapContentWidth(Alignment.End)
                        .padding(10.dp),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
            }
        }
        Row {
            game.description_raw?.let { desc ->
                Text(
                    text = desc,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                    softWrap = true
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
        ) {
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
        game.website?.let {
            WebsiteRow(website = game.website)
        }
    }
}

