package com.gym.ui.features.ejercicios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gym.models.repositorios.EjercicioRepository
import com.gym.models.toEjercicio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EjerciciosViewModel @Inject constructor(
    private val repository: EjercicioRepository
) : ViewModel() {
    private val _ejercicios = MutableStateFlow<List<EjercicioUiState>>(value = emptyList())
    val ejercicios = _ejercicios.asStateFlow()
    private val _ejercicioSeleccionado = MutableStateFlow<EjercicioUiState?>(value = null)
    val ejercicioSeleccionado = _ejercicioSeleccionado.asStateFlow()

    init {
        getEjercicios()
    }

    fun onEjercicioEvent(event: EjercicioEvent) {
        when (event) {
            is EjercicioEvent.OnGetEjercicios -> getEjercicios()
            is EjercicioEvent.OnGetEjercicioById -> getEjercicioById(id = event.id)
            is EjercicioEvent.OnInsertEjercicio -> insertEjercicio(ejercicio = event.ejercicio)
            is EjercicioEvent.OnUpdateEjercicio -> updateEjercicio(ejercicio = event.ejercicio)
            is EjercicioEvent.OnDeleteEjercicio -> deleteEjercicio(ejercicio = event.ejercicio)
        }
    }

    private fun getEjercicios() {
        viewModelScope.launch {
            _ejercicios.value = repository.getAll().map { it.toEjercicio().toEjercicioUiState() }
        }
    }

    private fun getEjercicioById(id: Int) {
        viewModelScope.launch {
            _ejercicioSeleccionado.value = repository.getById(id = id).toEjercicio().toEjercicioUiState()
        }
    }

    private fun insertEjercicio(ejercicio: EjercicioUiState) {
        viewModelScope.launch {
            repository.insert(ejercicio.toEjercicio())
            getEjercicios()
        }
    }

    private fun updateEjercicio(ejercicio: EjercicioUiState) {
        viewModelScope.launch {
            repository.update(ejercicio.toEjercicio())
            getEjercicios()
        }
    }

    private fun deleteEjercicio(ejercicio: EjercicioUiState) {
        viewModelScope.launch {
            repository.delete(ejercicio.toEjercicio())
            getEjercicios()
        }
    }
}