package com.gym.ui.features.historial

import com.gym.ui.features.registros.RegistroUiState
import java.time.LocalDate

sealed interface HistorialEvent {
    data class OnGetRegistroDelHistorial(val fecha : LocalDate?) : HistorialEvent
    data class OnGetRegistrosPorFecha(val fecha: LocalDate) : HistorialEvent
    data class OnUpdateRegistro(val registro: RegistroUiState) : HistorialEvent
    data object OnDeleteRegistro : HistorialEvent
}