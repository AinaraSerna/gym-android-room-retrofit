package com.gym.data.room.gym.registros

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.time.LocalDate

@Dao
interface RegistroDao {
    @Query(value = "SELECT * FROM registros")
    suspend fun getAll(): List<RegistroEntity>
    @Query(value = "SELECT * FROM registros WHERE id = :id")
    suspend fun getById(id: Int): RegistroEntity
    @Query(value = "SELECT * FROM registros WHERE cod_historial = :codHistorial")
    suspend fun getByCodHistorial(codHistorial: Int): List<RegistroEntity>
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(registroEntity: RegistroEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(registroEntity: RegistroEntity)
    @Delete
    suspend fun delete(registroEntity: RegistroEntity)
}