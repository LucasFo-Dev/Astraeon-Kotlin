package com.github.lucasfodev.astraeon.data.repository

import com.github.lucasfodev.astraeon.data.mock.MockData
import com.github.lucasfodev.astraeon.data.model.Satellite

class SatelliteRepository {
    fun getSatellites(): List<Satellite> = MockData.satellitesMock
    
    fun getSatelliteById(id: Int): Satellite? = 
        MockData.satellitesMock.find { it.id == id }
}