package com.github.lucasfodev.astraeon.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lucasfodev.astraeon.ui.components.*
import com.github.lucasfodev.astraeon.ui.navigation.Screen
import com.github.lucasfodev.astraeon.ui.theme.CyberCyan
import com.github.lucasfodev.astraeon.ui.theme.DeepSpaceBlue
import com.github.lucasfodev.astraeon.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigate: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    GlitchText(
                        text = "ASTRAEON COMMAND", 
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 4.sp
                        ),
                        color = CyberCyan
                    ) 
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = DeepSpaceBlue,
        bottomBar = {
            SystemTicker()
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            TacticalBackground()
            
            // Globo Holográfico no Fundo
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp)
                    .alpha(0.1f),
                contentAlignment = Alignment.Center
            ) {
                HolographicGlobe(modifier = Modifier.size(400.dp))
            }

            HUDFrame {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AnimatedVisibility(
                            visible = visible,
                            enter = fadeIn(tween(1000)) + expandHorizontally(tween(1000))
                        ) {
                            Column {
                                Text(
                                    text = "CENTRAL DE DEFESA",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = CyberCyan,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 2.sp
                                )
                                Text(
                                    text = "STATUS: SISTEMA OPERACIONAL",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.4f),
                                    fontSize = 10.sp
                                )
                            }
                        }
                        
                        RadarComponent()
                    }
                    
                    Spacer(modifier = Modifier.height(32.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        item {
                            AnimatedCard(index = 0, visible = visible) {
                                DashboardCard(
                                    title = "Satélites",
                                    value = state.satelliteCount.toString(),
                                    onClick = { onNavigate(Screen.Satellites.route) }
                                )
                            }
                        }
                        item {
                            AnimatedCard(index = 1, visible = visible) {
                                DashboardCard(
                                    title = "Alertas",
                                    value = state.criticalAlertCount.toString(),
                                    onClick = { onNavigate(Screen.Alerts.route) }
                                )
                            }
                        }
                        item {
                            AnimatedCard(index = 2, visible = visible) {
                                DashboardCard(
                                    title = "Sensores",
                                    value = state.sensorCount.toString(),
                                    onClick = { onNavigate(Screen.Ocean.route) }
                                )
                            }
                        }
                        item {
                            AnimatedCard(index = 3, visible = visible) {
                                DashboardCard(
                                    title = "Regiões",
                                    value = state.regionCount.toString(),
                                    onClick = { onNavigate(Screen.Satellites.route) }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    AnimatedVisibility(
                        visible = visible,
                        enter = slideInVertically(initialOffsetY = { it }) + fadeIn()
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            PrimaryButton(
                                text = "Lista de Satélites",
                                onClick = { onNavigate(Screen.Satellites.route) }
                            )
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                PrimaryButton(
                                    text = "Oceano",
                                    onClick = { onNavigate(Screen.Ocean.route) },
                                    modifier = Modifier.weight(1f)
                                )
                                PrimaryButton(
                                    text = "Alertas",
                                    onClick = { onNavigate(Screen.Alerts.route) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun AnimatedCard(index: Int, visible: Boolean, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(500, delayMillis = index * 100)) + 
                slideInVertically(tween(500, delayMillis = index * 100), initialOffsetY = { 50 })
    ) {
        content()
    }
}
