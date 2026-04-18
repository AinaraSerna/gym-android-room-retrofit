package com.gym.ui.features.historial

sealed interface HistorialEvent {
    data object OnGetHistorial : HistorialEvent
    data class OnGetHistorialById(val id : Int?) : HistorialEvent
}