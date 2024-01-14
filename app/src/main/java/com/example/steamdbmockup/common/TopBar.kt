package com.example.steamdbmockup.common


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material.icons.twotone.Search
import androidx.compose.ui.res.vectorResource

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navapp.Screens
import com.example.steamdbmockup.R
import com.example.steamdbmockup.ui.theme.Grey1

@Composable
fun TopBar(navController: NavController){
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Grey1 ,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = "Popular",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f),
                )

                IconButton(onClick = {
                    navController.navigate(Screens.SearchScreen.route)
                },
                    ) {
                    Icon(painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search",
                        tint = Color.White)
                }
            }
        }
    }
}