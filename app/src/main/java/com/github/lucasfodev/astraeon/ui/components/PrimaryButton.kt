package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lucasfodev.astraeon.ui.theme.CyberBlue
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import com.github.lucasfodev.astraeon.ui.theme.DeepSpaceBlue

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val infiniteTransition = rememberInfiniteTransition(label = "shine")
    val shineOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "offset"
    )

    val gradient = Brush.horizontalGradient(
        colors = if (enabled) listOf(CyberCyan, CyberBlue) else listOf(Color.Gray, Color.DarkGray)
    )

    val shineBrush = Brush.linearGradient(
        0f to Color.Transparent,
        0.5f to Color.White.copy(alpha = 0.4f),
        1f to Color.Transparent,
        start = androidx.compose.ui.geometry.Offset(shineOffset * 500f, 0f),
        end = androidx.compose.ui.geometry.Offset(shineOffset * 500f + 100f, 200f)
    )

    val tacticalShape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp)

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .border(1.dp, CyberCyan.copy(alpha = 0.5f), tacticalShape),
        enabled = enabled,
        shape = tacticalShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = DeepSpaceBlue
        ),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .background(shineBrush) // Camada de brilho animado
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "[ ",
                    color = DeepSpaceBlue.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = text.uppercase(),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp
                )
                Text(
                    text = " ]",
                    color = DeepSpaceBlue.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
