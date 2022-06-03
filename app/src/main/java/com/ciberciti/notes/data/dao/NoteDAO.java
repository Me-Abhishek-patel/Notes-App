package com.ciberciti.notes.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.ciberciti.notes.data.entities.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void Insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT  * FROM Note")
    LiveData<List<Note>> getAllNotes();


    @Query("SELECT * FROM Note WHERE noteId = :id")
    LiveData<Note> getNote(String id);


}
