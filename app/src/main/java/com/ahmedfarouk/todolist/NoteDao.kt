package com.ahmedfarouk.todolist

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Note)
    @Delete
    suspend fun delete(note :Note)
    @Update
    suspend fun update(note : Note)
     @Query("SELECT * FROM notesTable ORDER BY id asc")
     fun getAllNotes() : LiveData<List<Note>>

}