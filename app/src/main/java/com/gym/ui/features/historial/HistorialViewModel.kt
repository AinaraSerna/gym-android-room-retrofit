package com.gym.ui.features.historial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gym.data.room.gym.historial.HistorialConNombreSesionDTO
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

    private val _historial = MutableStateFlow<List<HistorialConNombreSesionDTO>>(value = emptyList())
    val historial = _historial.asStateFlow()
    private val _historialSeleccionado = MutableStateFlow<HistorialConNombreSesionDTO?>(value = null)
    val historialSeleccionado = _historialSeleccionado.asStateFlow()

    init {
        getHistorial()
    }

    fun onHistorialEvent(event: HistorialEvent) {
        when (event) {
            is HistorialEvent.OnGetHistorial -> getHistorial()
            is HistorialEvent.OnGetHistorialById -> getEntradaDelHistorial(event.id)
            is HistorialEvent.OnInsertHistorial -> insertHistorial(
                event.historialUiState,
                event.onResult
            )
            is HistorialEvent.OnDeleteRegistros -> deleteHistorial(event.id)
        }
    }

    private fun getHistorial() {
        viewModelScope.launch {
            _historial.value = repository.getAllConNombreSesion()
        }
    }

    private fun getEntradaDelHistorial(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                _historialSeleccionado.value = repository.getByIdConNombreSesion(id)
            } else {
                _historialSeleccionado.value = null
            }
        }
    }

    private fun insertHistorial(historialUiState: HistorialUiState, onResult: (Int) -> Unit) {
        viewModelScope.launch {
            val id = repository.insert(historialUiState.toHistorial())
            onResult(id)
        }
    }

    private fun deleteHistorial(id: Int) {
        viewModelScope.launch {
            val historialUiState = repository.getById(id)
            repository.delete(historialUiState.toHistorial())
        }
    }
}