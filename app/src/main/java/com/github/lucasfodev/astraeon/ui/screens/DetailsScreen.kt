package com.github.lucasfodev.astraeon.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.lucasfodev.astraeon.data.repository.AlertRepository
import com.github.lucasfodev.astraeon.data.repository.OceanRepository
import com.github.lucasfodev.astraeon.data.repository.SatelliteRepository
import com.github.lucasfodev.astraeon.ui.components.*
import com.github.lucasfodev.astraeon.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    type: String,
    id: Int,
    onBack: () -> Unit
) {
    val title: String
    val subtitle: String
    val content: @Composable ColumnScope.() -> Unit
    val accentColor: Color
    var gaugeValue by remember { mutableStateOf(0.7f) }

    when (type) {
        "satellite" -> {
            val item = SatelliteRepository().getSatelliteById(id)
            title = "ESPECIFICAÇÕES ORBITAIS"
            subtitle = item?.name ?: "Satélite"
            accentColor = CyberCyan
            gaugeValue = 0.88f
            content = {
                item?.let {
                    DetailItem("Status Operacional", it.status, accentColor, Icons.Default.CheckCircle)
                    DetailItem("Altitude Orbital", it.altitude, accentColor, Icons.Default.Height)
                    DataVisualizer("Estabilidade de Órbita", 0.92f, accentColor)
                    DetailItem("Região de Varredura", it.region, accentColor, Icons.Default.Public)
                    DetailItem("Missão Técnica", it.description, accentColor, Icons.Default.Description, true)
                }
            }
        }
        "ocean" -> {
            val item = OceanRepository().getSensorById(id)
            title = "TELEMETRIA OCEÂNICA"
            subtitle = item?.name ?: "Sensor"
            accentColor = CyberBlue
            gaugeValue = 0.65f
            content = {
                item?.let {
                    DetailItem("Localização Geográfica", it.location, accentColor, Icons.Default.LocationOn)
                    DetailItem("Temperatura Térmica", it.temperature, accentColor, Icons.Default.Thermostat)
                    DataVisualizer("Nível de Bateria", 0.74f, accentColor)
                    DetailItem("Magnitude das Ondas", it.waveHeight, accentColor, Icons.Default.Waves)
                    DetailItem("Nível de Alerta", it.riskLevel, accentColor, Icons.Default.Warning)
                    DetailItem("Sincronização", it.lastUpdate, accentColor, Icons.Default.Sync)
                }
            }
        }
        else -> {
            val item = AlertRepository().getAlertById(id)
            title = "RELATÓRIO DE ALERTA"
            subtitle = item?.title ?: "Alerta"
            accentColor = AlertRed
            gaugeValue = if(item?.severity == "Crítica") 0.95f else 0.6f
            content = {
                item?.let {
                    DetailItem("Nível de Severidade", it.severity, accentColor, Icons.Default.PriorityHigh)
                    DataVisualizer("Risco de Impacto", gaugeValue, accentColor)
                    DetailItem("Data da Ocorrência", it.date, accentColor, Icons.Default.Event)
                    DetailItem("Análise Técnica", it.description, accentColor, Icons.Default.Info, true)
                }
            }
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "flicker")
    val flickerAlpha by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(100), RepeatMode.Reverse),
        label = "alpha"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    GlitchText(
                        text = subtitle.uppercase(), 
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.sp
                        ),
                        color = Color.White
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
        containerColor = DeepSpaceBlue,
        bottomBar = { SystemTicker() }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            TacticalBackground()
            
            HUDFrame {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Header Técnico
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(10.dp, 2.dp).background(accentColor))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = accentColor,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                            Text(
                                text = "ENCRYPTED_DATA_STREAM",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.3f),
                                fontSize = 8.sp
                            )
                        }
                        
                        GaugeComponent(
                            label = "Signal",
                            value = gaugeValue,
                            accentColor = accentColor,
                            modifier = Modifier.size(80.dp)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))

                    // Main Content Card
                    val tacticalShape = CutCornerShape(topStart = 24.dp, bottomEnd = 24.dp)
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(tacticalShape)
                                .border(
                                    width = 1.dp,
                                    brush = Brush.linearGradient(listOf(accentColor.copy(alpha = 0.5f), Color.Transparent)),
                                    shape = tacticalShape
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = SurfaceLighter.copy(alpha = 0.4f)
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                verticalArrangement = Arrangement.spacedBy(28.dp)
                            ) {
                                content()
                            }
                        }
                        ScannerBeam(modifier = Modifier.matchParentSize().clip(tacticalShape))
                    }
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Decoração de Sistema Inferior
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Brush.horizontalGradient(listOf(Color.Transparent, accentColor.copy(alpha = 0.3f), Color.Transparent)))
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "NODE_ID: ${id}X-88 // VERIFIED_BY_ASTRAEON_ORBITAL",
                        modifier = Modifier.align(Alignment.CenterHorizontally).alpha(flickerAlpha),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.2f),
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}

@Composable
fun DetailItem(
    label: String, 
    value: String, 
    accentColor: Color, 
    icon: ImageVector,
    useTypewriter: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = accentColor,
            modifier = Modifier.size(20.dp).padding(top = 2.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = accentColor.copy(alpha = 0.6f),
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(4.dp))
            
            if (useTypewriter) {
                TypewriterText(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            } else {
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    lineHeight = 22.sp
                )
            }
        }
    }
}

@Composable
fun DataVisualizer(label: String, progress: Float, accentColor: Color) {
    val animatedProgress = remember { Animatable(0f) }
    LaunchedEffect(progress) {
        animatedProgress.animateTo(progress, tween(1500, easing = FastOutSlowInEasing))
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = accentColor.copy(alpha = 0.6f),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = "${(animatedProgress.value * 100).toInt()}%",
                style = MaterialTheme.typography.labelSmall,
                color = accentColor,
                fontFamily = FontFamily.Monospace
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(2.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedProgress.value)
                    .fillMaxHeight()
                    .background(
                        Brush.horizontalGradient(listOf(accentColor.copy(alpha = 0.3f), accentColor)),
                        RoundedCornerShape(2.dp)
                    )
            )
        }
    }
}
