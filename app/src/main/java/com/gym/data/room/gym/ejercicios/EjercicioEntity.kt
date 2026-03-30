package com.gym.data.room.gym.ejercicios

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.gym.data.room.gym.sesiones.SesionEntity

@Entity(
    tableName = "ejercicios",
    foreignKeys = [
        ForeignKey(
            entity = SesionEntity::class,
            parentColumns = ["id"],
            childColumns = ["cod_sesion"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class EjercicioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "cod_sesion")
    val codSesion: Int?,
    @ColumnInfo(name = "notas")
    val notas: String
)
