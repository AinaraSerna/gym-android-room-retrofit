package com.gym.data.room.gym.historial

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HistorialDao {
    @Query(value = "SELECT h.id, h.cod_sesion as codSesion, s.nombre as nombreSesion, h.fecha " +
            "FROM historial h INNER JOIN sesiones s ON h.cod_sesion = s.id " +
            "ORDER BY h.fecha DESC")
    suspend fun getAllConNombreSesion(): List<HistorialConNombreSesionDTO>

    @Query(value = "SELECT * FROM historial WHERE id = :id")
    suspend fun getById(id: Int): HistorialEntity

    @Query(value = "SELECT h.id, h.cod_sesion as codSesion, s.nombre as nombreSesion, h.fecha " +
            "FROM historial h INNER JOIN sesiones s ON h.cod_sesion = s.id " +
            "WHERE h.id = :id")
    suspend fun getByIdConNombreSesion(id: Int): HistorialConNombreSesionDTO

    @Query(value = "SELECT * FROM historial WHERE cod_sesion = :codSesion ORDER BY fecha DESC")
    suspend fun getByCodSesion(codSesion: Int): List<HistorialEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(historialEntity: HistorialEntity): Long

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(historialEntity: HistorialEntity)

    @Delete
    suspend fun delete(historialEntity: HistorialEntity)
}