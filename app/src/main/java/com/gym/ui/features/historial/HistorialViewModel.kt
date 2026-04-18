package com.gym.ui.features.historial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gym.models.repositorios.HistorialRepository
import com.gym.models.toHistorial
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistorialViewModel @Inject constructor(
    private val repository: HistorialRepository
) : ViewModel() {

    private val _historial = MutableStateFlow<List<HistorialUiState>>(value = emptyList())
    val historial = _historial.asStateFlow()
    private val _historialSeleccionado = MutableStateFlow<HistorialUiState?>(value = null)
    val historialSeleccionado = _historialSeleccionado.asStateFlow()

    init {
        getHistorial()
    }

    fun onHistorialEvent(event: HistorialEvent) {
        when (event) {
            is HistorialEvent.OnGetHistorial -> getHistorial()
            is HistorialEvent.OnGetHistorialById -> getEntradaDelHistorial(event.id)
        }
    }

    private fun getHistorial() {
        viewModelScope.launch {
            _historial.value = repository.getAll().map { it.toHistorial().toHistorialUiState() }
        }
    }

    private fun getEntradaDelHistorial(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                _historialSeleccionado.value = repository.getById(id).toHistorial().toHistorialUiState()
            } else {
                _historialSeleccionado.value = null
            }
        }
    }

}