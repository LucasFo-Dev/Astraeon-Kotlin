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
import androidx.compose.material.icons.filled.SatelliteAlt
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
import com.github.lucasfodev.astraeon.data.model.Satellite
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import com.github.lucasfodev.astraeon.ui.theme.SurfaceLighter
import com.github.lucasfodev.astraeon.ui.theme.TextGray

@Composable
fun SatelliteCard(
    satellite: Satellite,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedScale by animateFloatAsState(if (isPressed) 0.98f else 1f, label = "scale")

    val statusColor = if (satellite.status == "Ativo" || satellite.status == "Operacional") CyberCyan else Color.Red

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .scale(animatedScale)
            .neonGlow(statusColor.copy(alpha = 0.3f), borderRadius = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(listOf(statusColor.copy(alpha = 0.5f), Color.Transparent)),
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
                    .background(statusColor)
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
                        .background(statusColor.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.SatelliteAlt,
                        contentDescription = null,
                        tint = statusColor,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "ORB-${satellite.id}",
                            style = MaterialTheme.typography.labelSmall,
                            color = statusColor.copy(alpha = 0.6f),
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Sparkline(color = statusColor, modifier = Modifier.width(40.dp))
                    }
                    Text(
                        text = satellite.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = satellite.region.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = TextGray,
                        letterSpacing = 0.5.sp
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = satellite.status.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Black,
                        color = statusColor,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "LINK_OK",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.2f),
                        fontSize = 8.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}
