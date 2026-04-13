package com.gym.ui.features.historial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gym.models.repositorios.RegistrosRepository
import com.gym.data.room.gym.registros.HistorialRegistro
import com.gym.models.toRegistro
import com.gym.ui.features.registros.RegistroUiState
import com.gym.ui.features.registros.toRegistro
import com.gym.ui.features.registros.toRegistroUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HistorialViewModel @Inject constructor(
    private val repository: RegistrosRepository
) : ViewModel() {

    private val _historial = MutableStateFlow<List<HistorialRegistro>>(value = emptyList())
    val historial = _historial.asStateFlow()
    private val _entradaDeHistorialSeleccionado = MutableStateFlow<HistorialRegistro?>(value = null)
    val entradaDeHistorialSeleccionado = _entradaDeHistorialSeleccionado.asStateFlow()
    val registrosPorFecha = MutableStateFlow<List<RegistroUiState>>(value = emptyList())

    init {
        getHistorial()
    }

    fun onHistorialEvent(event: HistorialEvent) {
        when (event) {
            is HistorialEvent.OnGetHistorial -> getHistorial()
            is HistorialEvent.OnGetEntradaDelHistorial -> getEntradaDeHistorial(event.fecha)
            is HistorialEvent.OnUpdateRegistro -> updateRegistro(event.registro)
            is HistorialEvent.OnDeleteRegistros -> deleteRegistros()
        }
    }

    private fun getHistorial() {
        viewModelScope.launch {
            _historial.value = repository.getHistorial()
        }
    }

    private fun getEntradaDeHistorial(fecha: LocalDate?) {
        viewModelScope.launch {
            if (fecha == null) {
                _entradaDeHistorialSeleccionado.value = null
                registrosPorFecha.value = emptyList()
            } else {
                _entradaDeHistorialSeleccionado.value = repository.getRegistroDeHistorial(fecha)
                getRegistrosPorFecha(fecha)
            }
        }
    }

    private suspend fun getRegistrosPorFecha(fecha: LocalDate) {
        registrosPorFecha.value =
            repository.getRegistrosByFecha(fecha).map { it.toRegistro().toRegistroUiState() }
    }

    private fun updateRegistro(registro: RegistroUiState) {
        viewModelScope.launch {
            repository.update(registro = registro.toRegistro())
            getHistorial()
        }
    }

    private fun deleteRegistros() {
        viewModelScope.launch {
            registrosPorFecha.value.forEach {
                repository.delete(registro = it.toRegistro())
            }
            registrosPorFecha.value = emptyList()
            _entradaDeHistorialSeleccionado.value = null
            getHistorial()
        }
    }
}