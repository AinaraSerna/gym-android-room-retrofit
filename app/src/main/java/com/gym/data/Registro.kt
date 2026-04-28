package com.gym.data

data class Registro(
    val id : Int,
    val codHistorial : Int,
    val codEjercicio : Int?,
    val nombreEjercicio : String,
    val serie : Int,
    val peso : Float,
    val repeticiones : String
)
