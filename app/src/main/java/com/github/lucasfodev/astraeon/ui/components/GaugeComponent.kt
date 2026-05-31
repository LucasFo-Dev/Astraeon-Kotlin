package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan

@Composable
fun GaugeComponent(
    label: String,
    value: Float,
    modifier: Modifier = Modifier,
    accentColor: Color = CyberCyan
) {
    val animatedValue = remember { Animatable(0f) }
    LaunchedEffect(value) {
        animatedValue.animateTo(value, tween(1500, easing = FastOutSlowInEasing))
    }

    Box(modifier = modifier.size(120.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val stroke = 8.dp.toPx()
            
            // Background Circle
            drawArc(
                color = accentColor.copy(alpha = 0.1f),
                startAngle = 135f,
                sweepAngle = 270f,
                useCenter = false,
                style = Stroke(width = stroke, cap = StrokeCap.Round)
            )

            // Progress Arc
            drawArc(
                brush = Brush.sweepGradient(
                    listOf(accentColor.copy(alpha = 0.3f), accentColor)
                ),
                startAngle = 135f,
                sweepAngle = 270f * animatedValue.value,
                useCenter = false,
                style = Stroke(width = stroke, cap = StrokeCap.Round)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${(animatedValue.value * 100).toInt()}%",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = accentColor.copy(alpha = 0.6f),
                fontSize = 8.sp,
                letterSpacing = 1.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}
