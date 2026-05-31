package com.github.lucasfodev.astraeon.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay

@Composable
fun TypewriterText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    delayMillis: Long = 30L
) {
    var textToDisplay by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        textToDisplay = ""
        text.forEach { char ->
            textToDisplay += char
            delay(delayMillis)
        }
    }

    Text(
        text = textToDisplay,
        style = style,
        color = color,
        modifier = modifier
    )
}
