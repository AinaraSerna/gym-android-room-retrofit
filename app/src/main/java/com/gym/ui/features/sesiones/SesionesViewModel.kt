package com.gym.ui.features.sesiones

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.gym.models.repositorios.SesionRepository
import com.gym.models.toSesion
import com.gym.ui.navigation.sesiones.DetallesSesionRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SesionesViewModel @Inject constructor(
    private val repository: SesionRepository
) : ViewModel() {
    private var _sesiones = MutableStateFlow<List<SesionUiState>>(value = emptyList())
    val sesiones = _sesiones.asStateFlow()

    private var _sesionSeleccionada = MutableStateFlow<SesionUiState?>(value = null)
    val sesionSeleccionada = _sesionSeleccionada.asStateFlow()

    init {
        getSesiones()
    }

    fun onSesionEvent(event: SesionEvent) {
        when (event) {
            is SesionEvent.OnGetSesiones -> getSesiones()
            is SesionEvent.OnGetSesionById -> getSesionById(event.id)
            is SesionEvent.OnInsertSesion -> insertSesion(event.sesionUiState)
            is SesionEvent.OnUpdateSesion -> updateSesion(event.sesionUiState)
            is SesionEvent.OnDeleteSesion -> deleteSesion(event.sesionUiState)
        }
    }

    private fun getSesiones() {
        viewModelScope.launch {
            _sesiones.value = repository.getAll().map { it.toSesion().toSesionUiState() }
        }
    }

    private fun getSesionById(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                _sesionSeleccionada.value = repository.getById(id).toSesion().toSesionUiState()
            } else {
                _sesionSeleccionada.value = null
            }
        }
    }

    private fun insertSesion(sesionUiState: SesionUiState) {
        viewModelScope.launch {
            repository.insert(sesionUiState.toSesion())
            getSesiones()
        }
    }

    private fun updateSesion(sesionUiState: SesionUiState) {
        viewModelScope.launch {
            repository.update(sesionUiState.toSesion())
            _sesionSeleccionada.value = null
            getSesiones()
        }
    }

    private fun deleteSesion(sesionUiState: SesionUiState) {
        viewModelScope.launch {
            repository.delete(sesionUiState.toSesion())
            _sesionSeleccionada.value = null
            getSesiones()
        }
    }
}