package com.gym.ui.features.registros.formNuevosRegistros

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gym.models.repositorios.EjercicioRepository
import com.gym.models.repositorios.RegistrosRepository
import com.gym.models.toEjercicio
import com.gym.ui.features.ejercicios.EjercicioUiState
import com.gym.ui.features.ejercicios.toEjercicioUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormNuevosRegistrosViewModel @Inject constructor(
    private val registrosRepository: RegistrosRepository,
    private val ejerciciosRepository: EjercicioRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val codSesion = savedStateHandle.get<Int>("codSesion")
    private val _listaEjerciciosDeSesion = MutableStateFlow<List<EjercicioUiState>>(value = emptyList())
    val listaEjerciciosDeSesion = _listaEjerciciosDeSesion.asStateFlow()

    init {
        getListaEjerciciosDeSesion()
    }

    private fun getListaEjerciciosDeSesion() {
        viewModelScope.launch {
            _listaEjerciciosDeSesion.value = ejerciciosRepository.getByCodSesion(codSesion!!)
                .map { it.toEjercicio().toEjercicioUiState() }
        }
    }
}