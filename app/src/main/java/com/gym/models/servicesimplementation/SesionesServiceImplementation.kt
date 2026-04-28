package com.gym.models.servicesimplementation

import android.util.Log
import com.gym.data.retrofit.respuestasapi.RespuestaApi
import com.gym.data.retrofit.respuestasapi.SesionApi
import com.gym.data.retrofit.respuestasapi.TotalRegistrosApi
import com.gym.data.retrofit.services.SesionesService
import com.gym.models.ApiServicesException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SesionesServiceImplementation @Inject constructor(
    private val sesionesService: SesionesService
) {
    private val logTag: String = "OkHttp"

    suspend fun get(): List<SesionApi> {
        val mensajeError = "No se han podido obtener los contactos"
        try {
            val response = sesionesService.sesionesApi()
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                val dato = response.body()
                return dato ?: throw ApiServicesException("No hay datos")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }

    suspend fun countSesiones(): TotalRegistrosApi? {
        val mensajeError = "No se han podido obtener los contactos"
        try {
            val response = sesionesService.totalSesionesApi()
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                val dato = response.body()
                return dato
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                return null
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }

    suspend fun insert(sesion: SesionApi): RespuestaApi? {
        val mensajeError = "No se ha podido añadir el contacto"
        try {
            val response = sesionesService.insertarSesionApi(sesion)
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                Log.d(logTag, response.body()?.toString() ?: "No hay respuesta")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
        return null
    }

    suspend fun update(sesion: SesionApi): RespuestaApi? {
        val mensajeError = "No se ha podido actualizar el contacto"
        try {
            val response = sesionesService.actualizarSesionApi(sesion)
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                Log.d(logTag, response.body()?.toString() ?: "No hay respuesta")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
        return null
    }

    suspend fun delete(sesionApi: SesionApi): RespuestaApi? {
        val mensajeError = "No se ha podido borrar el contacto"
        try {
            val response = sesionesService.borrarSesionApi(sesionApi)
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                Log.d(logTag, response.body()?.toString() ?: "No hay respuesta")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
        return null
    }
}