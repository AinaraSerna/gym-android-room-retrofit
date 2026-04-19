package com.gym.ui.features.historial.formRegistrosDeHistorial

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gym.models.repositorios.EjercicioRepository
import com.gym.models.repositorios.RegistrosRepository
import com.gym.models.toEjercicio
import com.gym.models.toRegistro
import com.gym.ui.features.ejercicios.EjercicioUiState
import com.gym.ui.features.ejercicios.toEjercicioUiState
import com.gym.ui.features.registros.RegistroUiState
import com.gym.ui.features.registros.toRegistro
import com.gym.ui.features.registros.toRegistroUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormRegistrosEnHistorialViewModel @Inject constructor(
    private val ejercicioRepository: EjercicioRepository,
    private val registrosRepository: RegistrosRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val codSesion = savedStateHandle.get<Int>("codSesion")
    val codHistorial = savedStateHandle.get<Int>("codHistorial")
    private val _listaEjercicios = MutableStateFlow<List<EjercicioUiState>>(value = emptyList())
    val listaEjercicios = _listaEjercicios.asStateFlow()
    private val _registros = MutableStateFlow<List<RegistroUiState>>(value = emptyList())
    val registros = _registros.asStateFlow()

    init {
        getEjercicios()
        getRegistros()
    }

    fun onRegistrosEnHistorialEvent(event: RegistrosHistorialEvent) {
        when (event) {
            is RegistrosHistorialEvent.OnGetEjercicios -> getEjercicios()
            is RegistrosHistorialEvent.OnGetRegistrosHistorial -> getRegistros()
            is RegistrosHistorialEvent.OnUpdateRegistro -> updateRegistro(event.registroUiState)
        }
    }

    private fun getEjercicios() {
        viewModelScope.launch {
            if (codSesion != null) {
                _listaEjercicios.value = ejercicioRepository.getByCodSesion(codSesion)
                    .map { it.toEjercicio().toEjercicioUiState() }
            }
        }
    }

    private fun getRegistros() {
        if (codHistorial != null) {
            viewModelScope.launch {
                _registros.value = registrosRepository.getByCodHistorial(codHistorial)
                    .map { it.toRegistro().toRegistroUiState() }
            }
        }
    }

    private fun updateRegistro(registroUiState: RegistroUiState) {
        viewModelScope.launch {
            registrosRepository.update(registroUiState.toRegistro())
        }
    }
}