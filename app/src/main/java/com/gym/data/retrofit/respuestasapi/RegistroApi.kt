package com.gym.data.retrofit.respuestasapi

data class RegistroApi(
    val id : Int,
    val codHistorial : Int,
    val codEjercicio : Int?,
    val nombreEjercicio : String,
    val serie : Int,
    val peso : Float,
    val repeticiones : String
)
