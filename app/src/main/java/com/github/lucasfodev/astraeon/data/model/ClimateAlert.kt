package com.github.lucasfodev.astraeon.data.model

data class ClimateAlert(
    val id: Int,
    val title: String,
    val description: String,
    val severity: String,
    val date: String
)