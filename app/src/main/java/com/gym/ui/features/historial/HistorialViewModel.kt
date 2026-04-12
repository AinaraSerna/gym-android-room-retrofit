package com.gym.ui.features.historial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gym.models.repositorios.RegistrosRepository
import com.gym.data.room.gym.registros.HistorialRegistro
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

    private val _registroHistorialSeleccionado = MutableStateFlow<HistorialRegistro?>(value = null)
    val registroHistorialSeleccionado = _registroHistorialSeleccionado.asStateFlow()

    init {
        getHistorial()
    }

    fun onHistorialEvent(event: HistorialEvent) {
        when (event) {
            is HistorialEvent.OnGetRegistroDelHistorial -> getRegistroDeHistorial(event.fecha)
        }
    }

    private fun getHistorial() {
        viewModelScope.launch {
            _historial.value = repository.getHistorial()
        }
    }

    private fun getRegistroDeHistorial(fecha: LocalDate?) {
        viewModelScope.launch {
            if (fecha == null) {
                _registroHistorialSeleccionado.value = null
            } else {
                _registroHistorialSeleccionado.value = repository.getRegistroDeHistorial(fecha)
            }
        }
    }
}