package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan

@Composable
fun ScannerBeam(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "scanner")
    val yOffsetPercent by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "yOffset"
    )

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val beamY = maxHeight * yOffsetPercent
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .offset(y = beamY)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, CyberCyan.copy(alpha = 0.4f), Color.Transparent)
                    )
                )
        )
    }
}
