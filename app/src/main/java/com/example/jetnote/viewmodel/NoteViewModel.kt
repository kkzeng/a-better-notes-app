package com.example.jetnote.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.model.NoteModel
import com.example.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    // private val noteList = mutableStateListOf<NoteModel>()
    private val _noteList = MutableStateFlow<List<NoteModel>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes()
                .distinctUntilChanged()
                .collect { listOfNotes ->
                    if (listOfNotes.isNotEmpty()) {
                        _noteList.value = listOfNotes
                    }
                }
        }
    }

    fun addNote(note: NoteModel) = viewModelScope.launch { noteRepository.addNote(note) }

    fun updateNote(note: NoteModel) = viewModelScope.launch { noteRepository.updateNote(note) }
    fun removeNote(note: NoteModel) = viewModelScope.launch { noteRepository.deleteNote(note) }
}