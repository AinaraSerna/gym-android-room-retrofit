package com.gym.data.retrofit.services

import com.gym.data.retrofit.respuestasapi.RegistroApi
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface RegistrosService {
    @GET(value = "registros")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun obtenerRegistrosApi(): List<RegistroApi>

    @GET(value = "registros/count")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun totalRegistrosApi(): Int

    @POST
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun insertarRegistroApi(registroApi: RegistroApi)

    @PUT
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun actualizarRegistroApi(registroApi: RegistroApi)

    @DELETE
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun borrarRegistroApi(registroApi: RegistroApi)
}