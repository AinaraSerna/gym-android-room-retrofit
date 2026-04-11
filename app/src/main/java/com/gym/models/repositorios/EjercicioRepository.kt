package com.gym.models.repositorios

import com.gym.data.Ejercicio
import com.gym.data.room.gym.ejercicios.EjercicioDao
import com.gym.models.toEjercicioEntity
import javax.inject.Inject

class EjercicioRepository @Inject constructor(
    private var dao: EjercicioDao
) {
    suspend fun getAll() = dao.getAll()
    suspend fun getById(id: Int) = dao.getById(id)
    suspend fun getByCodSesion(codSesion: Int) = dao.getByCodSesion(codSesion)
    suspend fun insert(ejercicio: Ejercicio) =
        dao.insert(ejercicioEntity = ejercicio.toEjercicioEntity())
    suspend fun update(ejercicio: Ejercicio) =
        dao.update(ejercicioEntity = ejercicio.toEjercicioEntity())
    suspend fun delete(ejercicio: Ejercicio) =
        dao.delete(ejercicioEntity = ejercicio.toEjercicioEntity())
}