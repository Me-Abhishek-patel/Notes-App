package com.ciberciti.notes.ui;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.repository.NotesRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    NotesRepository notesRepository;
    LiveData<List<Note>> allNotes;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
    }

    public LiveData<List<Note>> getAllNotes() {
        return notesRepository.getAllNotes();
    }

    public LiveData<List<Note>> geUserNotes(int userId) {
        return notesRepository.getUserNotes(userId);
    }


    public boolean isUserLoggedIn() {
        return notesRepository.isUserLoggedIn();
    }

    public void logout() {
        notesRepository.removeSession();
    }

    public int getCurrentUserId() {
        return notesRepository.getCurrentUserId();

    }
}
