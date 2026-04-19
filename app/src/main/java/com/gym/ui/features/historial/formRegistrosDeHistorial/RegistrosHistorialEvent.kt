package com.gym.ui.features.historial.formRegistrosDeHistorial

import com.gym.ui.features.registros.RegistroUiState

sealed interface RegistrosHistorialEvent {
    data object OnGetEjercicios : RegistrosHistorialEvent
    data object OnGetRegistrosHistorial : RegistrosHistorialEvent
    data class OnUpdateRegistro(val registroUiState: RegistroUiState) : RegistrosHistorialEvent
}