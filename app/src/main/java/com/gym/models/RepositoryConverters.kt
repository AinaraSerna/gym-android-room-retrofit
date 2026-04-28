package com.gym.models

import com.gym.data.Ejercicio
import com.gym.data.Historial
import com.gym.data.Registro
import com.gym.data.Sesion
import com.gym.data.room.gym.ejercicios.EjercicioEntity
import com.gym.data.room.gym.historial.HistorialEntity
import com.gym.data.room.gym.registros.RegistroEntity
import com.gym.data.room.gym.sesiones.SesionEntity

fun Sesion.toSesionEntity() = SesionEntity(
    id = this.id,
    nombre = this.nombre,
    descripcion = this.descripcion
)

fun SesionEntity.toSesion() = Sesion(
    id = this.id,
    nombre = this.nombre,
    descripcion = this.descripcion
)

fun Ejercicio.toEjercicioEntity() = EjercicioEntity(
    id = this.id,
    nombre = this.nombre,
    orden = this.orden,
    serie = this.serie,
    codSesion = this.codSesion,
    notas = this.notas
)

fun EjercicioEntity.toEjercicio() = Ejercicio(
    id = this.id,
    nombre = this.nombre,
    orden = this.orden,
    serie = this.serie,
    codSesion = this.codSesion,
    notas = this.notas
)

fun Historial.toHistorialEntity() = HistorialEntity(
    id = this.id,
    codSesion = this.codSesion,
    fecha = this.fecha
)

fun HistorialEntity.toHistorial() = Historial(
    id = this.id,
    codSesion = this.codSesion,
    fecha = this.fecha
)

fun Registro.toRegistroEntity() = RegistroEntity(
    id = this.id,
    codHistorial = this.codHistorial,
    codEjercicio = this.codEjercicio,
    nombreEjercicio = this.nombreEjercicio,
    serie = this.serie,
    peso = this.peso,
    repeticiones = this.repeticiones
)

fun RegistroEntity.toRegistro() = Registro(
    id = this.id,
    codHistorial = this.codHistorial,
    codEjercicio = this.codEjercicio,
    nombreEjercicio = this.nombreEjercicio,
    serie = this.serie,
    peso = this.peso,
    repeticiones = this.repeticiones
)