package com.gym.data.room.gym.sesiones

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SesionDao {
    @Query(value = "SELECT * FROM sesiones")
    suspend fun getAll(): List<SesionEntity>
    @Query(value = "SELECT * FROM sesiones WHERE id = :id")
    suspend fun getById(id: Int): SesionEntity
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(sesionEntity: SesionEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(sesionEntity: SesionEntity)
    @Delete
    suspend fun delete(sesionEntity: SesionEntity)
}