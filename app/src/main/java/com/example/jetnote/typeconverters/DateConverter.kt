package com.example.jetnote.typeconverters

import androidx.room.TypeConverter
import java.util.Date

object DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}