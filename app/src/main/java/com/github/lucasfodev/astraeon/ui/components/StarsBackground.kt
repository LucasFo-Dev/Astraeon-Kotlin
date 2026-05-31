package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun StarsBackground() {
    val infiniteTransition = rememberInfiniteTransition(label = "stars_movement")
    
    // Animação de brilho
    val alphaAnim by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    // Animação de movimento lento (Paralaxe)
    val movementAnim by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(100000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "movement"
    )

    val stars = remember {
        List(100) {
            Triple(Random.nextFloat(), Random.nextFloat(), Random.nextFloat())
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        
        stars.forEach { (x, y, starSize) ->
            // Calcula posição com movimento contínuo
            val currentY = ((y * h) + movementAnim * (starSize * 0.5f)) % h
            
            drawCircle(
                color = Color.White.copy(alpha = alphaAnim * starSize),
                radius = (starSize * 1.5).dp.toPx(),
                center = Offset(x * w, currentY)
            )
        }
    }
}
