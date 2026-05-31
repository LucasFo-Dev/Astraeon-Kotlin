package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.github.lucasfodev.astraeon.ui.theme.CyberBlue
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import com.github.lucasfodev.astraeon.ui.theme.DeepSpaceBlue
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TacticalBackground() {
    Box(modifier = Modifier.fillMaxSize().background(DeepSpaceBlue)) {
        // Nebula Glows
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(CyberCyan.copy(alpha = 0.08f), Color.Transparent),
                        center = androidx.compose.ui.geometry.Offset(0f, 0f),
                        radius = 1200f
                    )
                )
                .blur(80.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(CyberBlue.copy(alpha = 0.08f), Color.Transparent),
                        center = androidx.compose.ui.geometry.Offset(1200f, 1500f),
                        radius = 1500f
                    )
                )
                .blur(80.dp)
        )
        
        // Grade Hexagonal
        Canvas(modifier = Modifier.fillMaxSize()) {
            val hexSize = 60.dp.toPx()
            val w = size.width
            val h = size.height
            val hexPath = Path()
            val gridColor = Color.White.copy(alpha = 0.03f)
            
            for (x in 0..(w / hexSize).toInt() + 1) {
                for (y in 0..(h / hexSize).toInt() + 1) {
                    val cx = x * hexSize * 1.5f
                    val cy = y * hexSize * 1.732f + (if (x % 2 == 1) hexSize * 0.866f else 0f)
                    
                    for (i in 0..5) {
                        val angle = Math.toRadians(60.0 * i).toFloat()
                        val px = cx + hexSize * cos(angle)
                        val py = cy + hexSize * sin(angle)
                        if (i == 0) hexPath.moveTo(px, py) else hexPath.lineTo(px, py)
                    }
                    hexPath.close()
                }
            }
            drawPath(path = hexPath, color = gridColor, style = Stroke(width = 1f))
        }

        StarsBackground()       // Estrelas
        DigitalNoise()          // Textura de ruído digital
        TechnicalDataOverlay()  // Dados nos cantos
        HudOverlay()            // Molduras e scanlines
    }
}
