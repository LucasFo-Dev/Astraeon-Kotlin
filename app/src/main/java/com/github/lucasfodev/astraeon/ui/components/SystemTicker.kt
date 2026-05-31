package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan

@Composable
fun SystemTicker() {
    val infiniteTransition = rememberInfiniteTransition(label = "ticker")
    val xOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "xOffset"
    )

    val tickerText = " >> SYSTEM STATUS: OPTIMAL // UPLINK: ESTABLISHED // ENCRYPTION: AES-256 // COORDINATES: 23.5505° S, 46.6333° W // VECTOR: ACTIVE // SATELLITE LINK: ACTIVE // "

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .background(Color.Black.copy(alpha = 0.5f))
            .clipToBounds(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(unbounded = true)
        ) {
            Text(
                text = tickerText + tickerText + tickerText,
                modifier = Modifier.offset(x = xOffset.dp),
                color = CyberCyan.copy(alpha = 0.6f),
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
                maxLines = 1,
                softWrap = false,
                fontSize = 10.sp
            )
        }
    }
}
