package com.example.steamdbmockup.common

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("SuspiciousIndentation")
@Composable
fun RoundedRectWithCenteredText(
    meta:String,
    backgroundColor:Color
){
    Canvas(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp, bottom = 16.dp)
            .background(Color.Transparent)) {
            val rectSize = Size(200f,100f)

            //val rectTopLeft = Offset(50f,
            //    (size.height - rectSize.height)/2)

            val rectTopLeft = Offset(
                size.width - rectSize.width - 50f,
                (size.height - rectSize.height) / 2 + 50f
            )

            drawRoundRect(
                color = backgroundColor,
                topLeft = rectTopLeft,
                cornerRadius = CornerRadius(20f,40f),
                size = rectSize
            )
            drawIntoCanvas {
                val text = "$meta"
                val textPaint = Paint().apply {
                    color = android.graphics.Color.WHITE
                    textSize = 50f
                    textAlign = Paint.Align.CENTER
                }
                val x = rectTopLeft.x + rectSize.width / 2
                val y = rectTopLeft.y + rectSize.height / 2
                    + (textPaint.descent() - textPaint.ascent()) / 2 - textPaint.descent()

                drawContext.canvas.nativeCanvas.drawText(text,x,y,textPaint)
            }
        }
    }
