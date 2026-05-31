package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun PostProcessingOverlay() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Efeito Vignette (Sombra nas bordas)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        0.0f to Color.Transparent,
                        1.0f to Color.Black.copy(alpha = 0.5f)
                    )
                )
        )
        
        // Efeito de Scanlines (Linhas horizontais de monitor)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 1f
            val spacing = 8f
            for (y in 0..size.height.toInt() step spacing.toInt()) {
                drawLine(
                    color = Color.White.copy(alpha = 0.02f),
                    start = androidx.compose.ui.geometry.Offset(0f, y.toFloat()),
                    end = androidx.compose.ui.geometry.Offset(size.width, y.toFloat()),
                    strokeWidth = strokeWidth
                )
            }
        }
    }
}
