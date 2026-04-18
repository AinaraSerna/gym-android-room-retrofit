package com.gym.data.room.gym.historial

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.gym.data.room.gym.sesiones.SesionEntity
import java.time.LocalDate

@Entity(
    tableName = "historial",
    foreignKeys = [
        ForeignKey(
            entity = SesionEntity::class,
            parentColumns = ["id"],
            childColumns = ["cod_sesion"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class HistorialEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "cod_sesion")
    val codSesion: Int?,
    @ColumnInfo(name = "fecha")
    val fecha: LocalDate
)