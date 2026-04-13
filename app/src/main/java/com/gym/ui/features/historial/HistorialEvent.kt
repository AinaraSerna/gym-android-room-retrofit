package com.gym.ui.features.historial

import com.gym.ui.features.registros.RegistroUiState
import java.time.LocalDate

sealed interface HistorialEvent {
    data object OnGetHistorial : HistorialEvent
    data class OnGetEntradaDelHistorial(val fecha : LocalDate?) : HistorialEvent
    data class OnUpdateRegistro(val registro: RegistroUiState) : HistorialEvent
    data object OnDeleteRegistros : HistorialEvent
}