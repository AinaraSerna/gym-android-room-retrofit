package com.gym.ui.features.registros

import com.gym.data.Registro
import java.time.LocalDate

data class RegistroUiState(
    val id : Int = 0,
    val codEjercicio : Int? = null,
    val nombreEjercicio : String = "",
    val fecha : LocalDate = LocalDate.now(),
    val serie : Int = 0,
    var peso : Float = 0f,
    var repeticiones : String = ""
)

fun Registro.toRegistroUiState() = RegistroUiState(
    id = this.id,
    codEjercicio = this.codEjercicio,
    nombreEjercicio = this.nombreEjercicio,
    fecha = this.fecha,
    serie = this.serie,
    peso = this.peso,
    repeticiones = this.repeticiones
)

fun RegistroUiState.toRegistro() = Registro(
    id = this.id,
    codEjercicio = this.codEjercicio,
    nombreEjercicio = this.nombreEjercicio,
    fecha = this.fecha,
    serie = this.serie,
    peso = this.peso,
    repeticiones = this.repeticiones
)
