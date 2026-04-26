package com.gym.data.retrofit.services

import com.gym.data.retrofit.respuestasapi.RespuestaApi
import com.gym.data.retrofit.respuestasapi.SesionApi
import com.gym.data.retrofit.respuestasapi.TotalRegistrosApi
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface SesionesService {
    @GET(value = "sesiones")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun sesionesApi(): Response<List<SesionApi>>

    @GET(value = "sesiones/count")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun totalSesionesApi(): Response<TotalRegistrosApi>

    @POST
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun insertarSesionApi(sesionApi: SesionApi) : Response<RespuestaApi>

    @PUT
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun actualizarSesionApi(sesionApi: SesionApi) : Response<RespuestaApi>

    @DELETE
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun borrarSesionApi(sesionApi: SesionApi) : Response<RespuestaApi>
}