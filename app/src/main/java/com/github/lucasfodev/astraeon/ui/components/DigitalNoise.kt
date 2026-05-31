package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import kotlin.random.Random

@Composable
fun DigitalNoise(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width.toInt()
        val height = size.height.toInt()
        
        if (width > 0 && height > 0) {
            for (i in 0..500) {
                drawCircle(
                    color = Color.White.copy(alpha = 0.03f),
                    radius = Random.nextFloat() * 1.5f,
                    center = androidx.compose.ui.geometry.Offset(
                        Random.nextInt(width).toFloat(),
                        Random.nextInt(height).toFloat()
                    )
                )
            }
        }
    }
}
