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
    val ejercicios: StateFlow<List<EjercicioUiState>> = _ejercicios.asStateFlow()
    private val _ejercicioSeleccionado = MutableStateFlow<EjercicioUiState?>(value = null)
    val ejercicioSeleccionado: StateFlow<EjercicioUiState?> = _ejercicioSeleccionado.asStateFlow()

    init {
        getEjercicios()
    }

    fun onEjercicioEvent(event: EjercicioEvent){
        when(event){
            is EjercicioEvent.OnGetEjercicios -> getEjercicios()
            is EjercicioEvent.OnGetEjercicioById -> getEjercicioById(id = event.id)
            is EjercicioEvent.OnGetBySesion -> getBySesion(codSesion = event.codSesion)
            is EjercicioEvent.OnCountEjerciciosPorSesion -> countEjerciciosPorSesion(codSesion = event.codSesion)
            is EjercicioEvent.OnInsertEjercicio -> insertEjercicio(ejercicio = event.ejercicio)
            is EjercicioEvent.OnUpdateEjercicio -> updateEjercicio(ejercicio = event.ejercicio)
            is EjercicioEvent.OnDeleteEjercicio -> deleteEjercicio(ejercicio = event.ejercicio)
        }
    }

    private fun getEjercicios(){
        viewModelScope.launch {
            _ejercicios.value = repository.getAll().map { it.toEjercicio().toEjercicioUiState() }
        }
    }

    private fun getEjercicioById(id: Int) {
        TODO("Not yet implemented")
    }

    private fun getBySesion(codSesion: Int) {
        TODO("Not yet implemented")
    }

    private fun countEjerciciosPorSesion(codSesion: Int) {
        TODO("Not yet implemented")
    }

    private fun insertEjercicio(ejercicio: EjercicioUiState) {
        TODO("Not yet implemented")
    }

    private fun updateEjercicio(ejercicio: EjercicioUiState) {
        TODO("Not yet implemented")
    }

    private fun deleteEjercicio(ejercicio: EjercicioUiState) {
        TODO("Not yet implemented")
    }
}