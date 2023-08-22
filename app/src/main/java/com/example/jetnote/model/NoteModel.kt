package com.example.jetnote.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "notes_table")
data class NoteModel(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    
    val title: String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)