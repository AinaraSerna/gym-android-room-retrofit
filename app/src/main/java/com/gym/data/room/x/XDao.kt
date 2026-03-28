package com.gym.data.room.x

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface XDao {
    @Query(value = "SELECT * FROM x")
    suspend fun getAll(): List<XEntity>
    @Query(value = "SELECT * FROM x WHERE id = :id")
    suspend fun getById(id: Int): XEntity
    @Insert
    suspend fun insert(x: XEntity)
    @Update
    suspend fun update(x: XEntity)
    @Delete
    suspend fun delete(x: XEntity)
    @Query(value = "DELETE FROM x WHERE id = :id")
    suspend fun deleteById(id: Int)
    @Query(value = "SELECT COUNT(*) FROM x")
    suspend fun count(): Int
}