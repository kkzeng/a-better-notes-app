package com.example.jetnote.data

import com.example.jetnote.model.NoteModel

object NotesDataSource {
    fun loadNotes(): List<NoteModel> {
        return listOf(
            NoteModel(
                title = "test",
                description = "desc"
            )
        )
    }
}