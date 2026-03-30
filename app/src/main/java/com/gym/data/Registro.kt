package com.gym.data

import java.util.Date

data class Registro(
    val id : Int,
    val codEjercicio : Int?,
    val nombreEjercicio : String,
    val fecha : Date,
    val serie : Int,
    val peso : Int,
    val repeticiones : Int,
)
