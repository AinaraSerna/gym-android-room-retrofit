package com.gym.data.room.gym

sealed class ResultadoDB {
    object Exito : ResultadoDB()
    data class Error(val mensaje: String, val tipo: TipoError) : ResultadoDB()
}

enum class TipoError {
    CONFLICTO_UNICIDAD,
    ERROR_DESCONOCIDO,
    CLAVE_FORANEA_INVALIDA
}