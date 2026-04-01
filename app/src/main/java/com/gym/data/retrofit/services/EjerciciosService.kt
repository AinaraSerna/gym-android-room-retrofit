package com.gym.data.retrofit.services

import androidx.room.Insert
import com.gym.data.retrofit.respuestasapi.EjercicioApi
import com.gym.data.retrofit.respuestasapi.TotalRegistrosApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface EjerciciosService {
    @GET(value = "ejercicios")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun ejerciciosApi(): Response<List<EjercicioApi>>

    @GET(value = "ejercicios/total")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun totalEjerciciosApi(): Response<TotalRegistrosApi>

    @Insert
    suspend fun insertarEjercicioApi(ejercicioApi: EjercicioApi)
}