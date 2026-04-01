package com.gym.data.retrofit.services

import androidx.room.Insert
import com.gym.data.retrofit.respuestasapi.SesionApi
import com.gym.data.retrofit.respuestasapi.TotalRegistrosApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface SesionesService {
    @GET(value = "sesiones")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun sesionesApi(): Response<List<SesionApi>>

    @GET(value = "sesiones/total")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun totalSesionesApi(): Response<TotalRegistrosApi>

    @Insert
    suspend fun insertarSesionApi(sesionApi: SesionApi)
}