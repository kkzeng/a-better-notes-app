package com.example.jetnote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetnote.model.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {
    @Query("SELECT * FROM notes_table")
    fun getNotes(): Flow<List<NoteModel>>

    @Query("SELECT * FROM notes_table where id=:id")
    suspend fun getNotesById(id: String): NoteModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: NoteModel)

    @Query("DELETE from notes_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: NoteModel)
}
