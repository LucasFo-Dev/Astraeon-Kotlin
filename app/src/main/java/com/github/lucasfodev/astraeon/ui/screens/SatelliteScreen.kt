package com.github.lucasfodev.astraeon.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lucasfodev.astraeon.ui.components.*
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import com.github.lucasfodev.astraeon.ui.theme.DeepSpaceBlue
import com.github.lucasfodev.astraeon.viewmodel.SatelliteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SatelliteScreen(
    viewModel: SatelliteViewModel,
    onBack: () -> Unit,
    onNavigateToDetails: (Int) -> Unit
) {
    val satellites by viewModel.satellites.collectAsState()
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    GlitchText(
                        text = "SISTEMA ORBITAL", 
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.sp
                        ),
                        color = CyberCyan
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = CyberCyan
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = DeepSpaceBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            TacticalBackground()
            
            HUDFrame { // Camada de dados técnicos nos cantos
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = "VETOR DE RASTREAMENTO ATIVO // PROTOCOLO_ALPHA",
                            style = MaterialTheme.typography.labelSmall,
                            color = CyberCyan.copy(alpha = 0.5f),
                            modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
                        )
                    }
                    itemsIndexed(satellites) { index, satellite ->
                        AnimatedVisibility(
                            visible = visible,
                            enter = fadeIn(tween(600, delayMillis = index * 80)) + 
                                    slideInHorizontally(tween(600, delayMillis = index * 80), initialOffsetX = { -30 })
                        ) {
                            SatelliteCard(
                                satellite = satellite,
                                onClick = { onNavigateToDetails(satellite.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
