package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.neonGlow(
    color: Color,
    borderRadius: Dp = 8.dp,
    blurRadius: Dp = 8.dp,
    spread: Dp = 2.dp
) = this.drawBehind {
    val shadowColor = color.copy(alpha = 0.3f).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()
    
    drawIntoCanvas { canvas ->
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            blurRadius.toPx(),
            0f, 0f,
            shadowColor
        )
        canvas.drawRoundRect(
            0f, 0f, size.width, size.height,
            borderRadius.toPx(), borderRadius.toPx(),
            paint
        )
    }
}
