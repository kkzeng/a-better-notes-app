package com.example.jetnote.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetnote.model.NoteModel

class NoteViewModel : ViewModel() {
    private val noteList = mutableStateListOf<NoteModel>()

    fun addNote(note: NoteModel) {
        noteList.add(note)
    }

    fun removeNote(note: NoteModel) {
        noteList.remove(note)
    }

    fun getNotes(): List<NoteModel> = noteList
}