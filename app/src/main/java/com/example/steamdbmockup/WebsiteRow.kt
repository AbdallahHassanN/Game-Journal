package com.example.steamdbmockup

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.steamdbmockup.ui.theme.Blue500

@Composable
fun WebsiteRow(website: String) {
    val context = LocalContext.current

    val underlinedText = AnnotatedString.Builder().apply {
        withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
            append("Visit Website")
        }
    }.toAnnotatedString()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                // Open the website in the browser when clicked
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(website))
                context.startActivity(intent)
            },
    ) {
        Text(
            text = underlinedText,

            style = MaterialTheme.typography.titleLarge,
            color = Blue500,
            softWrap = true
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null, // Decorative
            tint = Blue500
        )
    }
}