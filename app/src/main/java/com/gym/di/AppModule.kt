package com.gym.di

import android.content.Context
import android.content.SharedPreferences
import com.gym.data.room.x.XDao
import com.gym.models.SettingsRepository
import com.gym.models.XRepository
import com.efundae.moviles.nivel4.ud3.reto7.data.room.XBD
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
    fun provideDatabase(@ApplicationContext context: Context): XBD {
        return XBD.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideXDao(xBD: XBD): XDao {
        return xBD.xDao()
    }

    @Provides
    @Singleton
    fun provideXRepositorio(
        xDao: XDao
    ): XRepository = XRepository(xDao)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("App", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(sharedPreferences: SharedPreferences): SettingsRepository {
        return SettingsRepository(sharedPreferences)
    }

}