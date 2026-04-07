package com.gym.ui.features.ejercicios

import com.gym.data.Ejercicio

data class EjercicioUiState(
    val id: Int? = null,
    val orden : Int = 0,
    val nombre : String = "",
    val codSesion : Int? = null,
    val notas : String = ""
)

fun Ejercicio.toEjercicioUiState() = EjercicioUiState(
    id = this.id,
    nombre = this.nombre,
    orden = this.orden,
    codSesion = this.codSesion,
    notas = this.notas
)

fun EjercicioUiState.toEjercicio() = Ejercicio(
    id = this.id,
    nombre = this.nombre,
    orden = this.orden,
    codSesion = this.codSesion,
    notas = this.notas
)

