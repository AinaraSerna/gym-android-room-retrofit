package com.gym.data.room.gym.ejercicios

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gym.data.room.gym.TotalResultados

@Dao
interface EjercicioDao {
    @Query(value = "SELECT * FROM ejercicios")
    suspend fun getAll(): List<EjercicioEntity>

    @Query(value = "SELECT * FROM ejercicios WHERE id = :id")
    suspend fun getById(id: Int): EjercicioEntity

    @Query(value = "SELECT * FROM ejercicios WHERE cod_sesion = :codSesion")
    suspend fun getBySesion(codSesion: Int): List<EjercicioEntity>

//    @Query("SELECT s.nombre AS 'concepto', COUNT(e.cod_sesion) AS 'total' FROM sesiones s " +
//            "INNER JOIN ejercicios e ON s.id = e.cod_sesion GROUP BY e.cod_sesion")
//    suspend fun countEjerciciosPorSesion() : TotalResultados

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(ejercicioEntity: EjercicioEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(ejercicioEntity: EjercicioEntity)

    @Delete
    suspend fun delete(ejercicioEntity: EjercicioEntity)

}