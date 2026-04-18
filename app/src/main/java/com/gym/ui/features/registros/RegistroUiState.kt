package com.gym.ui.features.registros

import com.gym.data.Registro

data class RegistroUiState(
    val id : Int = 0,
    val codHistorial : Int = 0,
    val codEjercicio : Int? = null,
    val nombreEjercicio : String = "",
    val serie : Int = 0,
    var peso : Float = 0f,
    var repeticiones : String = ""
)

fun Registro.toRegistroUiState() = RegistroUiState(
    id = this.id,
    codHistorial = this.codHistorial,
    codEjercicio = this.codEjercicio,
    nombreEjercicio = this.nombreEjercicio,
    serie = this.serie,
    peso = this.peso,
    repeticiones = this.repeticiones
)

fun RegistroUiState.toRegistro() = Registro(
    id = this.id,
    codHistorial = this.codHistorial,
    codEjercicio = this.codEjercicio,
    nombreEjercicio = this.nombreEjercicio,
    serie = this.serie,
    peso = this.peso,
    repeticiones = this.repeticiones
)
