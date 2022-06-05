package com.ciberciti.notes.ui.notedetails;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.ciberciti.notes.data.dbutils.ImageList;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.repository.NotesRepository;

import java.util.ArrayList;

import static com.ciberciti.notes.utils.Constants.NOT_A_NOTE_ID;

public class NotesDetailsActivityViewModel extends AndroidViewModel {
    NotesRepository notesRepository;
    Integer noteId = NOT_A_NOTE_ID;

    public String title;
    public String description;
    ArrayList<ImageList> imageLists;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ImageList> getImageLists() {
        return imageLists;
    }

    public void setImageLists(ArrayList<ImageList> imageLists) {
        this.imageLists = imageLists;
    }

    public NotesDetailsActivityViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
    }

    void deleteNote() {
        notesRepository.deleteNote(noteId);
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public LiveData<Note> getNote() {
        return notesRepository.getCurrentNote(noteId);
    }

}
