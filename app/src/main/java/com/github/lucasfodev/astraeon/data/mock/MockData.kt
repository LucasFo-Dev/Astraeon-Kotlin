package com.github.lucasfodev.astraeon.data.mock

import com.github.lucasfodev.astraeon.data.model.ClimateAlert
import com.github.lucasfodev.astraeon.data.model.OceanSensor
import com.github.lucasfodev.astraeon.data.model.Satellite

object MockData {
    val satellitesMock = listOf(
        Satellite(1, "Jason-3", "Ativo", "1336 km", "Oceano Atlântico", "Monitora a topografia da superfície oceânica."),
        Satellite(2, "Sentinel-6", "Ativo", "1336 km", "Costa Brasileira", "Fornece medições de alta precisão da elevação do mar."),
        Satellite(3, "NOAA-20", "Operacional", "824 km", "América do Sul", "Coleta dados atmosféricos críticos para previsão do tempo."),
        Satellite(4, "Terra", "Operacional", "705 km", "Global", "Monitora o estado ambiental da Terra e as mudanças climáticas."),
        Satellite(5, "Aqua", "Operacional", "705 km", "Global", "Estuda o ciclo da água na Terra, incluindo evaporação e precipitação.")
    )

    val oceanSensorsMock = listOf(
        OceanSensor(1, "Boia Atlântico Norte", "29°C", "4.8 m", "CRÍTICO", "15 min atrás", "Localizada em zona de alta instabilidade térmica."),
        OceanSensor(2, "Boia Costa Brasileira", "26°C", "2.1 m", "MODERADO", "30 min atrás", "Monitoramento padrão da costa sudeste."),
        OceanSensor(3, "Sensor Pacífico Sul", "22°C", "1.5 m", "BAIXO", "1 hora atrás", "Condições de navegação estáveis."),
        OceanSensor(4, "Estação Antártica", "-2°C", "5.2 m", "CRÍTICO", "5 min atrás", "Alerta de formação de gelo e ondas gigantes."),
        OceanSensor(5, "Boia Caribe", "30°C", "3.0 m", "MODERADO", "10 min atrás", "Monitoramento de depressão tropical.")
    )

    val alertsMock = listOf(
        ClimateAlert(1, "Tempestade Marítima", "Previsão de ventos fortes e ondas acima de 5m.", "Alta", "22/11/2023"),
        ClimateAlert(2, "Elevação Costeira", "Risco de inundação em áreas de baixa altitude.", "Crítica", "23/11/2023"),
        ClimateAlert(3, "Onda de Calor Marinho", "Aumento anômalo da temperatura da água no Nordeste.", "Média", "24/11/2023"),
        ClimateAlert(4, "Ciclone Extratropical", "Formação de ciclone detectada via satélite Sentinel-6.", "Crítica", "25/11/2023"),
        ClimateAlert(5, "Baixa Umidade", "Alerta de seca severa detectado pelo satélite NOAA-20.", "Baixa", "26/11/2023")
    )
}
