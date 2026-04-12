package com.gym.ui.utils

import java.time.LocalDate

fun fechaFormatoHispano(fecha : LocalDate) : String {
    val salidaMes =
        if (fecha.monthValue.toString().length == 1) "0${fecha.monthValue}" else fecha.monthValue
    return "${fecha.dayOfMonth}/${salidaMes}/${fecha.year}"
}