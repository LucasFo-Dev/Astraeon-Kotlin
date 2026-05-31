package com.github.lucasfodev.astraeon.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lucasfodev.astraeon.ui.components.GlitchText
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import com.github.lucasfodev.astraeon.ui.theme.DeepSpaceBlue
import com.github.lucasfodev.astraeon.ui.theme.SurfaceBlue
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    val scale = remember { Animatable(0.5f) }
    val alpha = remember { Animatable(0f) }
    val bootLogs = remember { mutableStateListOf<String>() }
    
    val logs = listOf(
        "CORE_OS: INITIALIZING...",
        "UPLINK: SATELLITE_LINK_ESTABLISHED",
        "SECURITY: AES_256_ENABLED",
        "SENSORS: SCANNING_OCEAN_TIER_1",
        "ORBIT: CALIBRATING_VECTORS",
        "ASTRAEON: SYSTEM_READY"
    )

    LaunchedEffect(key1 = true) {
        scale.animateTo(1.0f, spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow))
        alpha.animateTo(1f, tween(800))
        
        // Simula logs subindo
        logs.forEach { log ->
            bootLogs.add(log)
            delay(400)
        }
        
        delay(800)
        onTimeout()
    }

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(DeepSpaceBlue, SurfaceBlue, DeepSpaceBlue)
    )

    Box(
        modifier = Modifier.fillMaxSize().background(backgroundGradient),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.alpha(alpha.value)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .scale(scale.value * 1.3f)
                        .background(CyberCyan.copy(alpha = 0.05f), androidx.compose.foundation.shape.CircleShape)
                )
                Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = null,
                    modifier = Modifier.size(90.dp).scale(scale.value),
                    tint = CyberCyan
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            GlitchText(
                text = "ASTRAEON",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = 10.sp
                )
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Terminal de Logs de Boot
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.height(100.dp)) {
                bootLogs.forEach { log ->
                    Text(
                        text = "> $log",
                        style = MaterialTheme.typography.labelSmall,
                        color = CyberCyan.copy(alpha = 0.6f),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 9.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            LinearProgressIndicator(
                modifier = Modifier.width(150.dp).height(1.dp),
                color = CyberCyan,
                trackColor = Color.White.copy(alpha = 0.05f)
            )
        }
    }
}
