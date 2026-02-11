package com.base.data.room

import androidx.room.TypeConverter
import java.time.LocalDate

class RoomConverters {
    @TypeConverter
    fun fromLocalDateToInt(value: LocalDate): Long {
        return value.toEpochDay()
    }

    @TypeConverter
    fun fromIntToLocaldate(value: Long): LocalDate {
        return LocalDate.ofEpochDay(value)
    }
}