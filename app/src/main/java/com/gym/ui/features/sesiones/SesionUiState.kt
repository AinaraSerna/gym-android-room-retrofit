package com.gym.ui.features.sesiones

import com.gym.data.Sesion

data class SesionUiState(
    val id: Int? = null,
    val nombre : String = "",
    val descripcion : String = ""
)

fun Sesion.toSesionUiState() = SesionUiState(
    id = id,
    nombre = nombre,
    descripcion = descripcion
)

fun SesionUiState.toSesion() = Sesion(
    id = id,
    nombre = nombre,
    descripcion = descripcion
)