package com.gym.ui.features.historial

import com.gym.data.Historial
import java.time.LocalDate

data class HistorialUiState(
    val id: Int = 0,
    val codSesion: Int? = null,
    val fecha: LocalDate = LocalDate.now()
)

fun Historial.toHistorialUiState() = HistorialUiState(
    id = this.id,
    codSesion = this.codSesion,
    fecha = this.fecha
)

fun HistorialUiState.toHistorial() = Historial(
    id = this.id,
    codSesion = this.codSesion,
    fecha = this.fecha
)