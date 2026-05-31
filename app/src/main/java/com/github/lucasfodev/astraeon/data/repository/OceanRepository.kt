package com.github.lucasfodev.astraeon.data.repository

import com.github.lucasfodev.astraeon.data.mock.MockData
import com.github.lucasfodev.astraeon.data.model.OceanSensor

class OceanRepository {
    fun getSensors(): List<OceanSensor> = MockData.oceanSensorsMock
    
    fun getSensorById(id: Int): OceanSensor? = 
        MockData.oceanSensorsMock.find { it.id == id }
}