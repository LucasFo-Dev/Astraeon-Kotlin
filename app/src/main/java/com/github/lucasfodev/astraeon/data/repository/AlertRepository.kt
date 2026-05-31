package com.github.lucasfodev.astraeon.data.repository

import com.github.lucasfodev.astraeon.data.mock.MockData
import com.github.lucasfodev.astraeon.data.model.ClimateAlert

class AlertRepository {
    fun getAlerts(): List<ClimateAlert> = MockData.alertsMock
    
    fun getAlertById(id: Int): ClimateAlert? = 
        MockData.alertsMock.find { it.id == id }
}