package com.gym.data.room.gym.historial

import java.time.LocalDate

data class HistorialConNombreSesionDTO(
    val id : Int,
    val codSesion : Int,
    val nombreSesion : String,
    val fecha : LocalDate
)
