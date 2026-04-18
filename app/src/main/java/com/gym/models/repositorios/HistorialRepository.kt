package com.gym.models.repositorios

import com.gym.data.Historial
import com.gym.data.room.gym.historial.HistorialDao
import com.gym.models.toHistorialEntity
import javax.inject.Inject

class HistorialRepository @Inject constructor(
    private val historialDao: HistorialDao
) {
    suspend fun getAll() = historialDao.getAll()
    suspend fun getById(id: Int) = historialDao.getById(id)
    suspend fun getByCodSesion(codSesion: Int) = historialDao.getByCodSesion(codSesion)
    suspend fun insert(historial: Historial) =
        historialDao.insert(historialEntity = historial.toHistorialEntity())

    suspend fun update(historial: Historial) =
        historialDao.update(historialEntity = historial.toHistorialEntity())

    suspend fun delete(historial: Historial) =
        historialDao.delete(historialEntity = historial.toHistorialEntity())
}