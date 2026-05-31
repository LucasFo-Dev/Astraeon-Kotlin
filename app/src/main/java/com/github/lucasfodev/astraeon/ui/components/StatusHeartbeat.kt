package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan

@Composable
fun StatusHeartbeat(modifier: Modifier = Modifier, color: Color = CyberCyan) {
    val infiniteTransition = rememberInfiniteTransition(label = "heartbeat")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "progress"
    )

    Canvas(modifier = modifier.width(100.dp).height(30.dp)) {
        val width = size.width
        val height = size.height
        val path = Path()

        path.moveTo(0f, height / 2)
        path.lineTo(width * 0.2f, height / 2)
        path.lineTo(width * 0.25f, height * 0.2f)
        path.lineTo(width * 0.35f, height * 0.8f)
        path.lineTo(width * 0.4f, height / 2)
        path.lineTo(width * 0.6f, height / 2)
        path.lineTo(width * 0.65f, height * 0.1f)
        path.lineTo(width * 0.75f, height * 0.9f)
        path.lineTo(width * 0.8f, height / 2)
        path.lineTo(width, height / 2)

        drawPath(
            path = path,
            color = color.copy(alpha = 0.1f),
            style = Stroke(width = 2.dp.toPx())
        )

        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = 2.dp.toPx(),
                pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                    floatArrayOf(20f, 1000f),
                    -progress * 1000f
                )
            )
        )
    }
}
