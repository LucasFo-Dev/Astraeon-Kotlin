package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun Sparkline(modifier: Modifier = Modifier, color: Color) {
    var data by remember { mutableStateOf(List(10) { Random.nextFloat() }) }

    // Simula a chegada de novos dados
    LaunchedEffect(Unit) {
        while (true) {
            delay(800)
            data = data.drop(1) + Random.nextFloat()
        }
    }

    val animatedData = data.map {
        animateFloatAsState(it, tween(800, easing = LinearEasing), label = "telemetry").value
    }

    Canvas(modifier = modifier.width(50.dp).height(20.dp)) {
        val path = Path()
        val width = size.width
        val height = size.height
        val stepX = width / (animatedData.size - 1)

        animatedData.forEachIndexed { index, value ->
            val x = index * stepX
            val y = height * (1f - (value * 0.7f + 0.15f))
            if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }

        drawPath(
            path = path,
            color = color.copy(alpha = 0.8f),
            style = Stroke(width = 1.5.dp.toPx())
        )
    }
}
