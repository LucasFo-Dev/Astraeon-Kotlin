package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan

@Composable
fun HudOverlay() {
    val infiniteTransition = rememberInfiniteTransition(label = "hud_scan")
    val scanlinePos by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "scanline"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        val color = CyberCyan.copy(alpha = 0.25f)
        val stroke = 1.dp.toPx()
        val corner = 40.dp.toPx()
        val pad = 20.dp.toPx()

        // Scanline horizontal animada
        drawLine(
            color = CyberCyan.copy(alpha = 0.08f),
            start = Offset(0f, scanlinePos * h),
            end = Offset(w, scanlinePos * h),
            strokeWidth = 2.dp.toPx()
        )

        // Linhas de Grade Técnicas (Subtle)
        for (i in 0..20) {
            val y = (h / 20) * i
            drawLine(Color.White.copy(alpha = 0.01f), Offset(0f, y), Offset(w, y), 1f)
        }

        // Colchetes de Mira HUD (Top Left)
        drawPath(Path().apply {
            moveTo(pad, pad + corner)
            lineTo(pad, pad)
            lineTo(pad + corner, pad)
            // Detalhe extra no canto
            moveTo(pad + 10.dp.toPx(), pad + 10.dp.toPx())
            lineTo(pad + 20.dp.toPx(), pad + 10.dp.toPx())
        }, color, style = Stroke(stroke))

        // Top Right
        drawPath(Path().apply {
            moveTo(w - pad - corner, pad)
            lineTo(w - pad, pad)
            lineTo(w - pad, pad + corner)
        }, color, style = Stroke(stroke))

        // Bottom Left
        drawPath(Path().apply {
            moveTo(pad, h - pad - corner)
            lineTo(pad, h - pad)
            lineTo(pad + corner, h - pad)
        }, color, style = Stroke(stroke))

        // Bottom Right
        drawPath(Path().apply {
            moveTo(w - pad - corner, h - pad)
            lineTo(w - pad, h - pad)
            lineTo(w - pad, h - pad - corner)
        }, color, style = Stroke(stroke))
        
        // Indicadores laterais (Decorative)
        drawLine(color, Offset(pad/2, h/2 - 50), Offset(pad/2, h/2 + 50), 1f)
        drawLine(color, Offset(w - pad/2, h/2 - 50), Offset(w - pad/2, h/2 + 50), 1f)
    }
}
