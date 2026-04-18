package com.gym.data.room.gym.registros

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gym.data.room.gym.ejercicios.EjercicioEntity
import com.gym.data.room.gym.historial.HistorialEntity
import java.time.LocalDate

@Entity(
    tableName = "registros",
    foreignKeys = [
        ForeignKey(
            entity = HistorialEntity::class,
            parentColumns = ["id"],
            childColumns = ["cod_historial"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = EjercicioEntity::class,
            parentColumns = ["id"],
            childColumns = ["cod_ejercicio"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index("cod_historial"),
        Index("cod_ejercicio")
    ]
)
data class RegistroEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "cod_historial")
    val codHistorial: Int,
    @ColumnInfo(name = "cod_ejercicio")
    val codEjercicio: Int?,
    @ColumnInfo(name = "nombre_ejercicio")
    val nombreEjercicio: String,
    @ColumnInfo(name = "serie")
    val serie: Int,
    @ColumnInfo(name = "peso")
    val peso: Float,
    @ColumnInfo(name = "repeticiones")
    val repeticiones: String
)
