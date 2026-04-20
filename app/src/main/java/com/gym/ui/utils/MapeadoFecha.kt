package com.gym.ui.utils

import java.time.LocalDate
import java.util.Locale

fun fechaLargaFormatoHispano(fecha: LocalDate): String {
    return "${
        traduccionDia(diaIngles = fecha.dayOfWeek.name).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
    }, ${fecha.dayOfMonth} de " +
            "${traduccionMes(mesIngles = fecha.month.name)} de ${fecha.year}"
}

fun fechaCortaFormatoHispano(fecha: LocalDate): String {
    val salidaDia =
        if (fecha.dayOfMonth.toString().length == 1) "0${fecha.dayOfMonth}" else "${fecha.dayOfMonth}"
    val salidaMes =
        if (fecha.monthValue.toString().length == 1) "0${fecha.monthValue}" else "${fecha.monthValue}"
    return "${salidaDia}/${salidaMes}/${fecha.year}"
}

private fun traduccionMes(mesIngles: String): String {
    return when (mesIngles.lowercase()) {
        "january" -> "enero"
        "february" -> "febrero"
        "march" -> "marzo"
        "april" -> "abril"
        "may" -> "mayo"
        "june" -> "junio"
        "july" -> "julio"
        "august" -> "agosto"
        "september" -> "septiembre"
        "october" -> "octubre"
        "november" -> "noviembre"
        "december" -> "diciembre"
        else -> mesIngles.lowercase()
    }
}

private fun traduccionDia(diaIngles: String): String {
    return when (diaIngles.lowercase()) {
        "monday" -> "lunes"
        "tuesday" -> "martes"
        "wednesday" -> "miércoles"
        "thursday" -> "jueves"
        "friday" -> "viernes"
        "saturday" -> "sábado"
        "sunday" -> "domingo"
        else -> diaIngles.lowercase()
    }
}