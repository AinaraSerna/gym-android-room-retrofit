package com.gym.data.room.gym.ejercicios

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface EjercicioDao {
    @Query(value = "SELECT * FROM ejercicios ORDER BY cod_sesion, orden, nombre")
    suspend fun getAll(): List<EjercicioEntity>

    @Query(value = "SELECT * FROM ejercicios WHERE id = :id")
    suspend fun getById(id: Int): EjercicioEntity

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(ejercicioEntity: EjercicioEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(ejercicioEntity: EjercicioEntity)

    @Delete
    suspend fun delete(ejercicioEntity: EjercicioEntity)

}