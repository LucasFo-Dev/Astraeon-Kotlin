package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun GlitchText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    var glitchOffset by remember { mutableStateOf(0.dp) }
    var glitchAlpha by remember { mutableStateOf(1f) }
    var showRedShadow by remember { mutableStateOf(false) }
    var showBlueShadow by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(Random.nextLong(2000, 5000))
            repeat(Random.nextInt(2, 5)) {
                glitchOffset = Random.nextInt(-4, 4).dp
                glitchAlpha = Random.nextFloat().coerceIn(0.7f, 1f)
                showRedShadow = Random.nextBoolean()
                showBlueShadow = Random.nextBoolean()
                delay(50)
            }
            glitchOffset = 0.dp
            glitchAlpha = 1f
            showRedShadow = false
            showBlueShadow = false
        }
    }

    Box(modifier = modifier) {
        if (showRedShadow) {
            Text(
                text = text,
                style = style,
                color = Color.Red.copy(alpha = 0.5f),
                modifier = Modifier.offset(x = (-2).dp, y = 1.dp)
            )
        }
        if (showBlueShadow) {
            Text(
                text = text,
                style = style,
                color = CyberCyan.copy(alpha = 0.5f),
                modifier = Modifier.offset(x = 2.dp, y = (-1).dp)
            )
        }
        Text(
            text = text,
            style = style,
            color = color,
            modifier = Modifier
                .alpha(glitchAlpha)
                .offset(x = glitchOffset)
        )
    }
}
