package com.ahmedfarouk.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel1 (application : Application):AndroidViewModel(application) {
    val allNotes :LiveData<List<Note>>
    val repository : noteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).wordDao()
        repository = noteRepository(dao)
        allNotes = repository.allNote
    }

    fun deleteNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun updateNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }
    fun addNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }

}