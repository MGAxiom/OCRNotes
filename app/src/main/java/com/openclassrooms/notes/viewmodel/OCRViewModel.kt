package com.openclassrooms.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.notes.model.Note
import com.openclassrooms.notes.repository.NotesRepository
import com.openclassrooms.notes.widget.NotesAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OCRViewModel(private val repository: NotesRepository) : ViewModel() {
    val notes: StateFlow<List<Note>> = repository.notes

    init {
        viewModelScope.launch {
            loadNotes()
        }
    }

    suspend fun loadNotes() {
            repository.loadNotes()
    }
}