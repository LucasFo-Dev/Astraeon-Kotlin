package com.github.lucasfodev.astraeon.viewmodel

import androidx.lifecycle.ViewModel
import com.github.lucasfodev.astraeon.data.model.ClimateAlert
import com.github.lucasfodev.astraeon.data.repository.AlertRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlertViewModel(
    private val repository: AlertRepository = AlertRepository()
) : ViewModel() {

    private val _alerts = MutableStateFlow<List<ClimateAlert>>(emptyList())
    val alerts: StateFlow<List<ClimateAlert>> = _alerts.asStateFlow()

    init {
        loadAlerts()
    }

    private fun loadAlerts() {
        _alerts.value = repository.getAlerts()
    }

    fun getAlertById(id: Int): ClimateAlert? {
        return repository.getAlertById(id)
    }
}