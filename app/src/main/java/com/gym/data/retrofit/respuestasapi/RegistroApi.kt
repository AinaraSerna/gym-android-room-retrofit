package com.gym.data.retrofit.respuestasapi

import java.time.LocalDate

data class RegistroApi(
    val id : Int,
    val codEjercicio : Int?,
    val nombreEjercicio : String,
    val fecha : LocalDate,
    val serie : Int,
    val peso : Int,
    val repeticiones : Int
)
