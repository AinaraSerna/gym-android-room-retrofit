package com.gym.di

import android.content.Context
import android.content.SharedPreferences
import com.gym.data.retrofit.services.EjerciciosService
import com.gym.data.retrofit.services.HistoricalService
import com.gym.data.retrofit.services.RegistrosService
import com.gym.data.retrofit.services.SesionesService
import com.gym.data.room.gym.sesiones.SesionDao
import com.gym.models.repositorios.SesionRepository
import com.gym.data.room.GymBD
import com.gym.data.room.gym.ejercicios.EjercicioDao
import com.gym.data.room.gym.historial.HistorialDao
import com.gym.data.room.gym.registros.RegistroDao
import com.gym.models.repositorios.EjercicioRepository
import com.gym.models.repositorios.HistorialRepository
import com.gym.models.repositorios.RegistrosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GymBD =
        GymBD.getDatabase(context)

    @Provides
    @Singleton
    fun provideSesionDao(gymBD: GymBD): SesionDao =
        gymBD.sesionDao()

    @Provides
    @Singleton
    fun provideEjercicioDao(gymBD: GymBD): EjercicioDao =
        gymBD.ejercicioDao()

    @Provides
    @Singleton
    fun provideHistorialDao(gymBD: GymBD): HistorialDao =
        gymBD.historialDao()

    @Provides
    @Singleton
    fun provideRegistroDao(gymBD: GymBD): RegistroDao =
        gymBD.registroDao()

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
    fun provideHistorialRepositorio(
        historialDao: HistorialDao
    ): HistorialRepository = HistorialRepository(historialDao)

    @Provides
    @Singleton
    fun provideRegistroRepositorio(
        registroDao: RegistroDao
    ): RegistrosRepository = RegistrosRepository(registroDao)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val timeout = 10L
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://192.168.0.101/gym/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideSesionesService(
        retrofit: Retrofit
    ) : SesionesService = retrofit.create(SesionesService::class.java)

    @Provides
    @Singleton
    fun provideEjerciciosService(
        retrofit: Retrofit
    ) : EjerciciosService = retrofit.create(EjerciciosService::class.java)

    @Provides
    @Singleton
    fun provideHistorialService(
        retrofit: Retrofit
    ) : HistoricalService = retrofit.create(HistoricalService::class.java)

    @Provides
    @Singleton
    fun provideRegistrosService(
        retrofit: Retrofit
    ) : RegistrosService = retrofit.create(RegistrosService::class.java)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("App", Context.MODE_PRIVATE)

}