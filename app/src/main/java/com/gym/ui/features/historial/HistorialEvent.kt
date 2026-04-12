package com.gym.ui.features.historial

import java.time.LocalDate

sealed interface HistorialEvent {
    data class OnGetRegistroDelHistorial(val fecha : LocalDate?) : HistorialEvent
}