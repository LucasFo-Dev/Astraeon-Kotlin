package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import kotlin.math.sin

@Composable
fun MiniWaveform(modifier: Modifier = Modifier, color: Color = CyberCyan) {
    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (2 * Math.PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "phase"
    )

    Canvas(modifier = modifier.width(60.dp).height(20.dp)) {
        val width = size.width
        val height = size.height
        val points = 20
        val path = Path()

        for (i in 0..points) {
            val x = (i.toFloat() / points) * width
            val y = (sin((i.toFloat() / points) * 2 * Math.PI + phase).toFloat() * (height / 3)) + (height / 2)
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }

        drawPath(
            path = path,
            color = color.copy(alpha = 0.5f),
            style = Stroke(width = 1.5.dp.toPx())
        )
    }
}
