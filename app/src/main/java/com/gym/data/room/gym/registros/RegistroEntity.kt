package com.gym.data.room.gym.registros

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.gym.data.room.gym.ejercicios.EjercicioEntity
import java.time.LocalDate

@Entity(
    tableName = "registros",
    foreignKeys = [
        ForeignKey(
            entity = EjercicioEntity::class,
            parentColumns = ["id"],
            childColumns = ["cod_ejercicio"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class RegistroEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "cod_ejercicio")
    val codEjercicio: Int?,
    @ColumnInfo(name = "nombre_ejercicio")
    val nombreEjercicio: String,
    @ColumnInfo(name = "fecha")
    val fecha: LocalDate,
    @ColumnInfo(name = "serie")
    val serie: Int,
    @ColumnInfo(name = "peso")
    val peso: Int,
    @ColumnInfo(name = "repeticiones")
    val repeticiones: Int
)
