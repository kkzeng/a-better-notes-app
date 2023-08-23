package com.example.jetnote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.jetnote.typeconverters.DateConverter
import java.time.Instant
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes_table")
@TypeConverters(DateConverter::class)
data class NoteModel(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "entry_date")
    val entryDate: Date = Date.from(Instant.now())
)