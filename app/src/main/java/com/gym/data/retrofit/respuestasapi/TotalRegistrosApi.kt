package com.gym.data.retrofit.respuestasapi

import com.google.gson.annotations.SerializedName

data class TotalRegistrosApi(
    @SerializedName(value = "tabla")
    val tabla: String,
    @SerializedName(value = "total_registros")
    val totalRegistros: Int
)