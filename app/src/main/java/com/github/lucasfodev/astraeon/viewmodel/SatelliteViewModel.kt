package com.github.lucasfodev.astraeon.viewmodel

import androidx.lifecycle.ViewModel
import com.github.lucasfodev.astraeon.data.model.Satellite
import com.github.lucasfodev.astraeon.data.repository.SatelliteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SatelliteViewModel(
    private val repository: SatelliteRepository = SatelliteRepository()
) : ViewModel() {

    private val _satellites = MutableStateFlow<List<Satellite>>(emptyList())
    val satellites: StateFlow<List<Satellite>> = _satellites.asStateFlow()

    init {
        loadSatellites()
    }

    private fun loadSatellites() {
        _satellites.value = repository.getSatellites()
    }

    fun getSatelliteById(id: Int): Satellite? {
        return repository.getSatelliteById(id)
    }
}