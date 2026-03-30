package com.gym.data

import java.time.LocalDate

data class Registro(
    val id : Int,
    val codEjercicio : Int?,
    val nombreEjercicio : String,
    val fecha : LocalDate,
    val serie : Int,
    val peso : Int,
    val repeticiones : Int,
)
