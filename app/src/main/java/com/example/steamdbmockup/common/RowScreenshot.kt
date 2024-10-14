package com.example.steamdbmockup.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.steamdbmockup.common.Constants.DEFAULT_Game_IMAGE
import com.example.steamdbmockup.model2.screenshots.gameScreenshots
import com.example.steamdbmockup.network.util.loadPicture

@Composable
fun RowScreenshot(
    screenshots: gameScreenshots?,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(start = 10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        screenshots?.image.let { url ->
            val image = url?.let {
                loadPicture(
                    url = it,
                    defaultImage = DEFAULT_Game_IMAGE
                ).value
            }
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "gameImage",
                    modifier = Modifier
                        .width(150.dp)
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}