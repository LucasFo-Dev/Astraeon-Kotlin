package com.github.lucasfodev.astraeon.data.model

data class Satellite(
    val id: Int,
    val name: String,
    val status: String,
    val altitude: String,
    val region: String,
    val description: String = "Satélite de monitoramento orbital de alta precisão."
)