package com.github.lucasfodev.astraeon.data.model

data class OceanSensor(
    val id: Int,
    val name: String,
    val location: String,
    val temperature: String,
    val waveHeight: String,
    val riskLevel: String,
    val lastUpdate: String
)