package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Water
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lucasfodev.astraeon.data.model.OceanSensor
import com.github.lucasfodev.astraeon.ui.theme.*

@Composable
fun SensorCard(
    sensor: OceanSensor,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedScale by animateFloatAsState(if (isPressed) 0.98f else 1f, label = "scale")

    val riskColor = when (sensor.riskLevel.uppercase()) {
        "CRÍTICO" -> AlertRed
        "MODERADO" -> WarningOrange
        else -> SafeGreen
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .scale(animatedScale)
            .neonGlow(CyberBlue.copy(alpha = 0.3f), borderRadius = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(listOf(CyberBlue.copy(alpha = 0.5f), Color.Transparent)),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceLighter.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
                    .background(riskColor)
            )

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(CyberBlue.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Water,
                        contentDescription = null,
                        tint = CyberBlue,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "SNR-0${sensor.id}",
                            style = MaterialTheme.typography.labelSmall,
                            color = CyberBlue.copy(alpha = 0.7f),
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Sparkline(color = CyberCyan, modifier = Modifier.width(40.dp))
                    }
                    Text(
                        text = sensor.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = sensor.location.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = TextGray,
                        letterSpacing = 0.5.sp
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = sensor.temperature,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = CyberCyan,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = sensor.riskLevel,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = riskColor,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}
