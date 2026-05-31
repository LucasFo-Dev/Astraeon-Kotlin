package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lucasfodev.astraeon.ui.theme.CyberBlue
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import com.github.lucasfodev.astraeon.ui.theme.SurfaceLighter

@Composable
fun DashboardCard(
    title: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(if (isPressed) 0.96f else 1f, label = "scale")

    val tacticalShape = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
    
    // Aplicando o brilho neon personalizado
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp)
            .scale(scale)
            .neonGlow(CyberCyan.copy(alpha = 0.5f), borderRadius = 16.dp)
            .clip(tacticalShape)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(listOf(CyberCyan, CyberBlue.copy(alpha = 0.2f))),
                shape = tacticalShape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceLighter.copy(alpha = 0.6f)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MiniWaveform(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .alpha(0.4f),
                color = CyberCyan
            )

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = CyberCyan,
                    letterSpacing = 2.sp
                )

                Text(
                    text = value.padStart(2, '0'),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = (-1).sp
                )
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "SYS_LINK_STABLE",
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 7.sp,
                        color = Color.White.copy(alpha = 0.3f),
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}
