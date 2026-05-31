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
fun TechnicalDataOverlay() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Dados no canto superior direito
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 80.dp, end = 20.dp)
                .alpha(0.2f),
            horizontalAlignment = Alignment.End
        ) {
            RepeatingTechText("VECTOR_X")
            RepeatingTechText("SIGNAL_STRENGTH")
            RepeatingTechText("UPLINK_BUFFER")
        }

        // Dados no canto inferior esquerdo
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 60.dp, start = 20.dp)
                .alpha(0.2f)
        ) {
            RepeatingTechText("LAT_COORD")
            RepeatingTechText("LON_COORD")
            RepeatingTechText("ALT_VAL")
        }
    }
}

@Composable
fun RepeatingTechText(label: String) {
    var value by remember { mutableStateOf("0000") }
    LaunchedEffect(Unit) {
        while (true) {
            delay(Random.nextLong(500, 2000))
            value = if (label.contains("COORD")) {
                "${Random.nextInt(-90, 90)}.${Random.nextInt(1000, 9999)}°"
            } else {
                Random.nextInt(1000, 9999).toString(16).uppercase()
            }
        }
    }

    Text(
        text = "$label: $value",
        style = MaterialTheme.typography.labelSmall,
        color = CyberCyan,
        fontSize = 7.sp,
        fontFamily = FontFamily.Monospace
    )
}
