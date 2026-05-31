package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HolographicGlobe(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "globe")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Canvas(modifier = modifier.size(200.dp)) {
        val center = center
        val radius = size.minDimension / 2
        val color = CyberCyan.copy(alpha = 0.3f)

        // Círculo base (Equador)
        drawCircle(color = color, radius = radius, style = Stroke(width = 1.dp.toPx()))

        // Linhas de Longitude animadas
        for (i in 0..5) {
            val angleOffset = Math.toRadians((rotation + i * 36).toDouble())
            val ellipseWidth = radius * cos(angleOffset).toFloat()
            
            drawOval(
                color = color.copy(alpha = 0.1f + (0.2f * kotlin.math.abs(cos(angleOffset).toFloat()))),
                topLeft = androidx.compose.ui.geometry.Offset(center.x - ellipseWidth, center.y - radius),
                size = androidx.compose.ui.geometry.Size(ellipseWidth * 2, radius * 2),
                style = Stroke(width = 1.dp.toPx())
            )
        }

        // Linhas de Latitude
        for (i in 1..4) {
            val h = (radius * (i / 5f))
            val rVal = kotlin.math.sqrt(radius * radius - h * h)
            
            drawCircle(color = color.copy(alpha = 0.1f), radius = rVal, center = androidx.compose.ui.geometry.Offset(center.x, center.y + h), style = Stroke(width = 0.5.dp.toPx()))
            drawCircle(color = color.copy(alpha = 0.1f), radius = rVal, center = androidx.compose.ui.geometry.Offset(center.x, center.y - h), style = Stroke(width = 0.5.dp.toPx()))
        }
    }
}
