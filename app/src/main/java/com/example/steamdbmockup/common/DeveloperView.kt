package com.example.steamdbmockup.common

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.steamdbmockup.common.Constants.DEFAULT_Game_IMAGE
import com.example.steamdbmockup.common.Constants.IMAGE_HEIGHT
import com.example.steamdbmockup.model2.Developer
import com.example.steamdbmockup.network.util.loadPicture
import com.example.steamdbmockup.ui.theme.Grey2

@SuppressLint("SuspiciousIndentation")
@Composable
fun DeveloperView(
    developer: Developer,
    loading: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey2)
    ) {
        if (loading) {
            CircularProgressBar()
        } else {
            developer.image_background.let {
                val image = loadPicture(url = it, defaultImage = DEFAULT_Game_IMAGE)
                    .value
                image?.let {
                    Image(
                        bitmap = it.asImageBitmap(), contentDescription = "Dev Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(IMAGE_HEIGHT.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        TextTitle(
            text = developer.name, modifier = Modifier.padding(10.dp),
            color = Color.White, fontSize = 20
        )
        TextTitle(
            text = "Games Developed: ${developer.games_count}",
            modifier = Modifier.padding(10.dp),
            color = Color.White,
            fontSize = 20
        )
        CustomDivider()
        Row {
            Text(
                text = developer.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
                softWrap = true
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DisplayDeveloperView() {
    val developer = Developer(
        games_count = 1,
        id = 1,
        image_background = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
        name = "Valve",
        slug = "valve",
        description = "\\u003Cp\\u003ERocksteady Studios Limited is a British video game developer and a subsidiary of Warner Bros. Interactive Entertainment; founded in 2004. It was previously co-owned by Square Enix but is currently wholly controlled by Warner Bros. They are best known for their Batman: Arkham series. It is an action-adventure game developed published in conjunction with Eidos Interactive. It is based on the DC Comics superhero Batman who&#39;s goal is to fight his way through an asylum which Joker wants to control and to stop him from detonating bombs around Gotham. The first game Rocksteady ever released is Urban Chaos: Riot Response (2006), a first-person shooter for PlayStation and Xbox. The players control a member of the &quot;T-Zero&quot; riot squad in an unnamed American city. The aim is to defeat the police by whatever means and to rescue injured civilians coming along.\\u003C/p\\u003E"
    )
    DeveloperView(developer = developer, loading = false)
}