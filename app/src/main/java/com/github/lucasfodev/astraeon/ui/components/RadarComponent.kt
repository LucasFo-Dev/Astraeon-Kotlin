package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun RadarComponent(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "radar")
    
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulse"
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    // Simulando "Blips" de satélites detectados
    val blips = remember {
        List(4) {
            Offset(Random.nextFloat(), Random.nextFloat())
        }
    }

    Canvas(modifier = modifier.size(100.dp)) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2

        // Círculos concêntricos HUD
        for (i in 1..3) {
            drawCircle(
                color = CyberCyan.copy(alpha = 0.05f * i),
                radius = radius * (i / 3f),
                center = center,
                style = Stroke(width = 1.dp.toPx())
            )
        }

        // Blips detectados
        blips.forEach { blip ->
            val bx = center.x + (blip.x * radius * 0.8f) * cos(Math.toRadians(blip.y.toDouble() * 360)).toFloat()
            val by = center.y + (blip.x * radius * 0.8f) * sin(Math.toRadians(blip.y.toDouble() * 360)).toFloat()
            
            // O brilho do blip aumenta quando a linha de radar passa por ele
            // (Simulação simplificada com fade constante)
            drawCircle(
                color = CyberCyan.copy(alpha = pulse * 0.5f),
                radius = 2.dp.toPx(),
                center = Offset(bx, by)
            )
        }

        // Linha de varredura
        val endX = center.x + radius * cos(Math.toRadians(rotation.toDouble())).toFloat()
        val endY = center.y + radius * sin(Math.toRadians(rotation.toDouble())).toFloat()
        
        drawLine(
            color = CyberCyan,
            start = center,
            end = Offset(endX, endY),
            strokeWidth = 2.dp.toPx()
        )
        
        // Pulso de eco
        drawCircle(
            color = CyberCyan.copy(alpha = 0.3f * (1f - pulse)),
            radius = radius * pulse,
            center = center,
            style = Stroke(width = 1.dp.toPx())
        )
    }
}
