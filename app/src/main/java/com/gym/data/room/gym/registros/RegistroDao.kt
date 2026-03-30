package com.gym.data.room.gym.registros

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.Date

@Dao
interface RegistroDao {
    @Query(value = "SELECT * FROM registros")
    suspend fun getAll(): List<RegistroEntity>
    @Query(value = "SELECT * FROM registros WHERE id = :id")
    suspend fun getById(id: Int): RegistroEntity
    @Query(value = "SELECT * FROM registros WHERE fecha = :fecha")
    suspend fun getByFecha(fecha: Date): List<RegistroEntity>
    @Insert
    suspend fun insert(registroEntity: RegistroEntity)
    @Update
    suspend fun update(registroEntity: RegistroEntity)
    @Delete
    suspend fun delete(registroEntity: RegistroEntity)
}