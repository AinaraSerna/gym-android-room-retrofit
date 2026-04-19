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
import kotlinx.coroutines.flow.update
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
    private val registros = _registros.asStateFlow()

    // Estados locales para la edición
    private val _datosPeso = MutableStateFlow<Map<String, String>>(emptyMap())
    val datosPeso = _datosPeso.asStateFlow()

    private val _datosReps = MutableStateFlow<Map<String, String>>(emptyMap())
    val datosReps = _datosReps.asStateFlow()

    private val _algunCambio = MutableStateFlow(value = false)
    val algunCambio = _algunCambio.asStateFlow()

    init {
        getEjercicios()
        getRegistros()
    }

    fun onRegistrosEnHistorialEvent(event: RegistrosHistorialEvent) {
        when (event) {
            is RegistrosHistorialEvent.OnGetEjercicios -> getEjercicios()
            is RegistrosHistorialEvent.OnGetRegistrosHistorial -> getRegistros()
            is RegistrosHistorialEvent.OnUpdateLocalData -> updateLocalData(
                event.key,
                event.peso,
                event.reps
            )

            is RegistrosHistorialEvent.OnSaveAll -> saveAll(event.onComplete)
        }
    }

    private fun updateLocalData(key: String, peso: String?, reps: String?) {
        peso?.let { nuevoPeso ->
            _datosPeso.update { it + (key to nuevoPeso) }
        }
        reps?.let { nuevasReps ->
            _datosReps.update { it + (key to nuevasReps) }
        }
        checkIfChanged()
    }

    private fun formatFloat(value: Float): String {
        return if (value % 1.0f == 0.0f) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }

    private fun checkIfChanged() {
        val currentPesos = _datosPeso.value
        val currentReps = _datosReps.value
        val originalRegistros = _registros.value

        _algunCambio.value = originalRegistros.any { registro ->
            val key = "${registro.codEjercicio}-${registro.serie}"
            val pesoActual = currentPesos[key] ?: ""
            val repsActual = currentReps[key] ?: ""

            val pesoOriginal = formatFloat(registro.peso)
            val repsOriginal = registro.repeticiones

            pesoActual != pesoOriginal || repsActual != repsOriginal
        }
    }

    private fun saveAll(onComplete: () -> Unit) {
        viewModelScope.launch {
            val currentPesos = _datosPeso.value
            val currentReps = _datosReps.value

            registros.value.forEach { registro ->
                val key = "${registro.codEjercicio}-${registro.serie}"
                val nuevoPeso = currentPesos[key]?.toFloatOrNull() ?: registro.peso
                val nuevasReps = currentReps[key] ?: registro.repeticiones

                if (nuevoPeso != registro.peso || nuevasReps != registro.repeticiones) {
                    registrosRepository.update(
                        registro.copy(peso = nuevoPeso, repeticiones = nuevasReps).toRegistro()
                    )
                }
            }
            onComplete()
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
                val listaRegistros = registrosRepository.getByCodHistorial(codHistorial)
                    .map { it.toRegistro().toRegistroUiState() }
                _registros.value = listaRegistros

                // Inicializar datos locales si están vacíos
                if (_datosPeso.value.isEmpty()) {
                    val inicialPesos = mutableMapOf<String, String>()
                    val inicialReps = mutableMapOf<String, String>()
                    listaRegistros.forEach { r ->
                        val key = "${r.codEjercicio}-${r.serie}"
                        inicialPesos[key] = formatFloat(r.peso)
                        inicialReps[key] = r.repeticiones
                    }
                    _datosPeso.value = inicialPesos
                    _datosReps.value = inicialReps
                }
                checkIfChanged()
            }
        }
    }

    private fun updateRegistro(registroUiState: RegistroUiState) {
        viewModelScope.launch {
            registrosRepository.update(registroUiState.toRegistro())
        }
    }
}