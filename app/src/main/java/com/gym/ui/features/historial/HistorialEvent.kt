package com.gym.ui.features.historial

sealed interface HistorialEvent {
    data object OnGetHistorial : HistorialEvent
    data class OnGetHistorialById(val id : Int?) : HistorialEvent
    data class OnInsertHistorial(val historialUiState: HistorialUiState, val onResult: (Int) -> Unit) : HistorialEvent
    data class OnDeleteRegistros(val id : Int) : HistorialEvent
}