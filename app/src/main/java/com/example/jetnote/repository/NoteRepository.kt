package com.example.jetnote.repository

import com.example.jetnote.data.NoteDatabaseDao
import com.example.jetnote.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val databaseDao: NoteDatabaseDao) {
    suspend fun addNote(note: NoteModel) = databaseDao.insert(note)
    suspend fun updateNote(note: NoteModel) = databaseDao.update(note)
    suspend fun deleteNote(note: NoteModel) = databaseDao.deleteNote(note)
    suspend fun deleteAllNotes() = databaseDao.deleteAll()
    suspend fun getAllNotes() = databaseDao.getNotes().flowOn(Dispatchers.IO).conflate()

}