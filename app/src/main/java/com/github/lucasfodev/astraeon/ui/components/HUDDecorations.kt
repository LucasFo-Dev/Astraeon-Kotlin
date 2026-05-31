package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun CornerData(modifier: Modifier = Modifier) {
    var hex by remember { mutableStateOf("0xFF42A") }
    
    LaunchedEffect(Unit) {
        while(true) {
            delay(200)
            hex = "0x${Random.nextInt(0xFFFFF).toString(16).uppercase()}"
        }
    }

    Column(modifier = modifier.alpha(0.3f)) {
        Text(
            text = "MEM_ADDR: $hex",
            style = MaterialTheme.typography.labelSmall,
            color = CyberCyan,
            fontSize = 8.sp,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = "LAT_SEC: ${Random.nextInt(10, 99)}.${Random.nextInt(1000, 9999)}",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            fontSize = 8.sp,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun HUDFrame(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        CornerData(Modifier.align(Alignment.TopStart).padding(16.dp))
        content()
        CornerData(Modifier.align(Alignment.BottomEnd).padding(bottom = 40.dp, end = 16.dp))
    }
}
