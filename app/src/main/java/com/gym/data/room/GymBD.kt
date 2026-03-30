package com.gym.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gym.data.room.gym.ejercicios.EjercicioDao
import com.gym.data.room.gym.ejercicios.EjercicioEntity
import com.gym.data.room.gym.registros.RegistroDao
import com.gym.data.room.gym.registros.RegistroEntity
import com.gym.data.room.gym.sesiones.SesionDao
import com.gym.data.room.gym.sesiones.SesionEntity

@Database(
    entities = [SesionEntity::class, EjercicioEntity::class, RegistroEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
abstract class GymBD : RoomDatabase() {
    abstract fun sesionDao(): SesionDao
    abstract fun ejercicioDao(): EjercicioDao
    abstract fun registroDao() : RegistroDao

    companion object {
        const val DATABASE_NAME = "gym.db"
        fun getDatabase(context: Context): GymBD {
            return Room.databaseBuilder(
                context = context,
                klass = GymBD::class.java,
                name = DATABASE_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}