package com.gym.models.repositorios

import com.gym.data.Historial
import com.gym.data.room.gym.historial.HistorialConNombreSesionDTO
import com.gym.data.room.gym.historial.HistorialDao
import com.gym.models.toHistorialEntity
import javax.inject.Inject

class HistorialRepository @Inject constructor(
    private val historialDao: HistorialDao
) {
    suspend fun getAllConNombreSesion() = historialDao.getAllConNombreSesion()
    suspend fun getById(id: Int) = historialDao.getById(id)
    suspend fun getByIdConNombreSesion(id: Int): HistorialConNombreSesionDTO = historialDao.getByIdConNombreSesion(id)
    suspend fun getByCodSesion(codSesion: Int) = historialDao.getByCodSesion(codSesion)
    suspend fun insert(historial: Historial): Int =
        historialDao.insert(historialEntity = historial.toHistorialEntity()).toInt()

    suspend fun update(historial: Historial) =
        historialDao.update(historialEntity = historial.toHistorialEntity())

    suspend fun delete(historial: Historial) =
        historialDao.delete(historialEntity = historial.toHistorialEntity())
}