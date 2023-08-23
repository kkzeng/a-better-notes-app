package com.example.jetnote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetnote.model.NoteModel

@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}