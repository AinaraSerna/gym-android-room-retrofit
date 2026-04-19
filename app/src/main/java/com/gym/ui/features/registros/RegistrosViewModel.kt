package com.gym.ui.features.registros

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gym.models.repositorios.SesionRepository
import com.gym.models.toSesion
import com.gym.ui.features.sesiones.SesionUiState
import com.gym.ui.features.sesiones.toSesionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrosViewModel @Inject constructor(
    private val repository: SesionRepository
) : ViewModel() {

    private val _sesiones = MutableStateFlow<List<SesionUiState>>(value = emptyList())
    val sesiones = _sesiones.asStateFlow()
    private var _sesionRegistrosSeleccionda = MutableStateFlow<SesionUiState?>(value = null)
    val sesionRegistrosSeleccionada = _sesionRegistrosSeleccionda.asStateFlow()

    private val _salirDeForm = MutableStateFlow<Boolean>(value = false)
    val salirDeForm = _salirDeForm.asStateFlow()

    init {
        getSesiones()
    }

    fun onRegistrosEvent(event: RegistrosEvent) {
        when (event) {
            is RegistrosEvent.OnGetSesiones -> getSesiones()
            is RegistrosEvent.OnGetSesionById -> getSesionById(event.id)
            is RegistrosEvent.OnInsertRegistro -> {}
            is RegistrosEvent.OnSalirDeForm -> _salirDeForm.value = event.salir
        }
    }

    private fun getSesiones(){
        viewModelScope.launch {
            _sesiones.value = repository.getAll().map { it.toSesion().toSesionUiState() }
        }
    }
    private fun getSesionById(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                _sesionRegistrosSeleccionda.value = repository.getById(id).toSesion().toSesionUiState()
            } else {
                _sesionRegistrosSeleccionda.value = null
            }
        }
    }
}

