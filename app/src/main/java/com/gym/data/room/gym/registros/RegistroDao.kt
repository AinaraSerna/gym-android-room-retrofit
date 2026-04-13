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

    @Query(value = "select sesiones.nombre as 'nombreSesion', registros.fecha from registros inner join ejercicios " +
            "inner join sesiones on registros.cod_ejercicio = ejercicios.id and ejercicios.cod_sesion = sesiones.id " +
            "group by registros.fecha order by registros.fecha desc")
    suspend fun getHistorial(): List<HistorialRegistro>

    @Query(value = "select sesiones.nombre as 'nombreSesion', registros.fecha from registros inner join ejercicios " +
            "inner join sesiones on registros.cod_ejercicio = ejercicios.id and ejercicios.cod_sesion = sesiones.id " +
            "group by registros.fecha having registros.fecha = :fecha")
    suspend fun getRegistroDeHistorial(fecha: LocalDate) : HistorialRegistro

    @Query(value = "SELECT * FROM registros WHERE fecha = :fecha")
    suspend fun getRegistrosByFecha(fecha: LocalDate): List<RegistroEntity>
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(registroEntity: RegistroEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(registroEntity: RegistroEntity)
    @Delete
    suspend fun delete(registroEntity: RegistroEntity)
}