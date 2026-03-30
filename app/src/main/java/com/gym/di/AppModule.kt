package com.gym.di

import android.content.Context
import android.content.SharedPreferences
import com.gym.data.room.gym.sesiones.SesionDao
import com.gym.models.repositorios.SesionRepository
import com.gym.data.room.GymBD
import com.gym.data.room.gym.ejercicios.EjercicioDao
import com.gym.data.room.gym.registros.RegistroDao
import com.gym.models.repositorios.EjercicioRepository
import com.gym.models.repositorios.RegistroRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GymBD {
        return GymBD.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideSesionDao(gymBD: GymBD): SesionDao {
        return gymBD.sesionDao()
    }

    @Provides
    @Singleton
    fun provideEjercicioDao(gymBD: GymBD): EjercicioDao {
        return gymBD.ejercicioDao()
    }

    @Provides
    @Singleton
    fun provideRegistroDao(gymBD: GymBD): RegistroDao {
        return gymBD.registroDao()
    }

    @Provides
    @Singleton
    fun provideSesionRepositorio(
        sesionDao: SesionDao
    ): SesionRepository = SesionRepository(sesionDao)

    @Provides
    @Singleton
    fun provideEjercicioRepositorio(
        ejercicioDao: EjercicioDao
    ): EjercicioRepository = EjercicioRepository(ejercicioDao)

    @Provides
    @Singleton
    fun provideRegistroRepositorio(
        registroDao: RegistroDao
    ): RegistroRepository = RegistroRepository(registroDao)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("App", Context.MODE_PRIVATE)
    }

}