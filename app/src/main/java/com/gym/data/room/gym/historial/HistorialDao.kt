package com.gym.data.room.gym.historial

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HistorialDao {
    @Query(value = "SELECT * FROM historial ORDER BY fecha DESC")
    suspend fun getAll(): List<HistorialEntity>

    @Query(value = "SELECT * FROM historial WHERE id = :id")
    suspend fun getById(id: Int): HistorialEntity

    @Query(value = "SELECT * FROM historial WHERE cod_sesion = :codSesion")
    suspend fun getByCodSesion(codSesion: Int): List<HistorialEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(historialEntity: HistorialEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(historialEntity: HistorialEntity)

    @Delete
    suspend fun delete(historialEntity: HistorialEntity)
}