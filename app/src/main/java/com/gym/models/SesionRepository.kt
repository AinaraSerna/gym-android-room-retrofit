package com.gym.models

import com.gym.data.Sesion
import com.gym.data.room.gym.sesiones.SesionDao
import javax.inject.Inject

class SesionRepository @Inject constructor(
    private var dao: SesionDao
) {
    suspend fun getAll() = dao.getAll()
    suspend fun getById(id: Int) = dao.getById(id)
    suspend fun insert(sesion: Sesion) = dao.insert(sesionEntity = sesion.toSesionEntity())
    suspend fun update(sesion: Sesion) = dao.update(sesionEntity = sesion.toSesionEntity())
    suspend fun delete(sesion: Sesion) = dao.delete(sesionEntity = sesion.toSesionEntity())
}