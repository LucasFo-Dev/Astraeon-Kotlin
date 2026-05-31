package com.github.lucasfodev.astraeon.viewmodel

import androidx.lifecycle.ViewModel
import com.github.lucasfodev.astraeon.data.repository.AlertRepository
import com.github.lucasfodev.astraeon.data.repository.OceanRepository
import com.github.lucasfodev.astraeon.data.repository.SatelliteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeState(
    val satelliteCount: Int = 0,
    val criticalAlertCount: Int = 0,
    val sensorCount: Int = 0,
    val regionCount: Int = 0
)

class HomeViewModel(
    private val satRepo: SatelliteRepository = SatelliteRepository(),
    private val oceanRepo: OceanRepository = OceanRepository(),
    private val alertRepo: AlertRepository = AlertRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        _state.value = HomeState(
            satelliteCount = satRepo.getSatellites().size,
            criticalAlertCount = alertRepo.getAlerts().count { 
                it.severity.equals("Crítica", ignoreCase = true) || it.severity.equals("Alta", ignoreCase = true) 
            },
            sensorCount = oceanRepo.getSensors().size,
            regionCount = satRepo.getSatellites().map { it.region }.distinct().size
        )
    }
}