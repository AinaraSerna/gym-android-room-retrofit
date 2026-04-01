package com.gym.data.retrofit.services

import androidx.room.Insert
import com.gym.data.retrofit.respuestasapi.RegistroApi
import retrofit2.http.GET
import retrofit2.http.Headers

interface RegistrosService {
    @GET(value = "registros")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun obtenerRegistrosApi(): List<RegistroApi>

    @GET(value = "registros/total")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun totalRegistrosApi(): Int

    @Insert
    suspend fun insertarRegistroApi(registroApi: RegistroApi)
}