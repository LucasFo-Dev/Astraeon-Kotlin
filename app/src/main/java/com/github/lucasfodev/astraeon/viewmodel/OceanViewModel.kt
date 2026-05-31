package com.github.lucasfodev.astraeon.viewmodel

import androidx.lifecycle.ViewModel
import com.github.lucasfodev.astraeon.data.model.OceanSensor
import com.github.lucasfodev.astraeon.data.repository.OceanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OceanViewModel(
    private val repository: OceanRepository = OceanRepository()
) : ViewModel() {

    private val _sensors = MutableStateFlow<List<OceanSensor>>(emptyList())
    val sensors: StateFlow<List<OceanSensor>> = _sensors.asStateFlow()

    init {
        loadSensors()
    }

    private fun loadSensors() {
        _sensors.value = repository.getSensors()
    }

    fun getSensorById(id: Int): OceanSensor? {
        return repository.getSensorById(id)
    }
}