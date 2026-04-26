package com.gym.data.retrofit.services

import com.gym.data.retrofit.respuestasapi.EjercicioApi
import com.gym.data.retrofit.respuestasapi.TotalRegistrosApi
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface EjerciciosService {
    @GET(value = "ejercicios")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun ejerciciosApi(): Response<List<EjercicioApi>>

    @GET(value = "ejercicios/count")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun totalEjerciciosApi(): Response<TotalRegistrosApi>

    @POST
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun insertarEjercicioApi(ejercicioApi: EjercicioApi)

    @PUT
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun actualizarEjercicioApi(ejercicioApi: EjercicioApi)

    @DELETE
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun borrarEjercicioApi(ejercicioApi: EjercicioApi)
}