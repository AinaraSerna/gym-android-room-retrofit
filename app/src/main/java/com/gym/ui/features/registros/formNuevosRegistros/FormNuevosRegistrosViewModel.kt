package com.gym.ui.features.registros.formNuevosRegistros

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gym.models.repositorios.EjercicioRepository
import com.gym.models.repositorios.HistorialRepository
import com.gym.models.repositorios.RegistrosRepository
import com.gym.models.toEjercicio
import com.gym.models.toRegistro
import com.gym.ui.features.ejercicios.EjercicioUiState
import com.gym.ui.features.ejercicios.toEjercicioUiState
import com.gym.ui.features.registros.RegistroUiState
import com.gym.ui.features.registros.RegistrosEvent
import com.gym.ui.features.registros.toRegistro
import com.gym.ui.features.registros.toRegistroUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormNuevosRegistrosViewModel @Inject constructor(
    private val registrosRepository: RegistrosRepository,
    private val ejerciciosRepository: EjercicioRepository,
    private val historialRepository: HistorialRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val codSesion = savedStateHandle.get<Int>("codSesion")
    private val _listaEjerciciosDeSesion =
        MutableStateFlow<List<EjercicioUiState>>(value = emptyList())
    val listaEjerciciosDeSesion = _listaEjerciciosDeSesion.asStateFlow()
    private val _ultimosRegistros = MutableStateFlow<Map<String, Pair<String, String>>>(value = emptyMap())
    val ultimosRegistros = _ultimosRegistros.asStateFlow()

    init {
        getListaEjerciciosDeSesion()
        getUltimosRegistros()
    }

    fun onRegistrosEvent(event: RegistrosEvent) {
        when (event) {
            is RegistrosEvent.OnInsertRegistro -> insertRegistro(event.registroUiState)
            else -> {}
        }
    }

    private fun getListaEjerciciosDeSesion() {
        viewModelScope.launch {
            _listaEjerciciosDeSesion.value = ejerciciosRepository.getByCodSesion(codSesion!!)
                .map { it.toEjercicio().toEjercicioUiState() }
        }
    }

    private fun insertRegistro(registroUiState: RegistroUiState) {
        viewModelScope.launch {
            registrosRepository.insert(registroUiState.toRegistro())
        }
    }

    private fun getUltimosRegistros(){
        viewModelScope.launch {
            val codHistorial = historialRepository.getByCodSesion(codSesion!!).first().id
            val registros = registrosRepository.getByCodHistorial(codHistorial)
                .map { it.toRegistro().toRegistroUiState() }
            registros.forEach {
                _ultimosRegistros.value += "${it.codEjercicio}-${it.serie}" to (it.peso.toString() to it.repeticiones)
            }
        }
    }
}