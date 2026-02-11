package com.efundae.moviles.nivel4.ud3.reto7.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.base.data.room.RoomConverters
import com.base.data.room.x.XDao
import com.base.data.room.x.XEntity

@Database(
    entities = [XEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class XBD : RoomDatabase() {
    abstract fun xDao(): XDao

    companion object {
        const val DATABASE_NAME = "x.db"
        fun getDatabase(context: Context): XBD {
            return Room.databaseBuilder(
                context = context,
                klass = XBD::class.java,
                name = DATABASE_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}