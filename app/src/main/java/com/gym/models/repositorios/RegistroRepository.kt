package com.gym.models.repositorios

import com.gym.data.Registro
import com.gym.data.room.gym.registros.RegistroDao
import com.gym.models.toRegistroEntity
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject


class RegistroRepository @Inject constructor(
    private val registroDao: RegistroDao
) {
    suspend fun getAll() = registroDao.getAll()
    suspend fun getById(id: Int) = registroDao.getById(id)
    suspend fun getByFecha(fecha: LocalDate) = registroDao.getByFecha(fecha)
    suspend fun insert(registro: Registro) = registroDao.insert(registroEntity = registro.toRegistroEntity())
    suspend fun update(registro: Registro) = registroDao.update(registroEntity = registro.toRegistroEntity())
    suspend fun delete(registro: Registro) = registroDao.delete(registroEntity = registro.toRegistroEntity())
}