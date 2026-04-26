package com.gym.data.retrofit.services

import com.gym.data.retrofit.respuestasapi.HistorialApi
import com.gym.data.retrofit.respuestasapi.TotalRegistrosApi
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface HistoricalService {
    @GET(value = "historial")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun historialApi() : Response<List<HistorialApi>>

    @GET(value = "historial/count")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun totalHistorialApi() : Response<TotalRegistrosApi>

    @POST
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun insertarHistorialApi(historialApi: HistorialApi)

    @PUT
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun actualizarHistorialApi(historialApi: HistorialApi)

    @DELETE
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun borrarHistorialApi(historialApi: HistorialApi)
}