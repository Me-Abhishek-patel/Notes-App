package com.ciberciti.notes.ui.addnote;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.ciberciti.notes.data.dbutils.ImageList;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.repository.NotesRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class AddNoteActivityViewModel extends AndroidViewModel {
    public String title;
    public String description;
    ArrayList<ImageList> imageLists;
    NotesRepository notesRepository;
    String TAG = AddNoteActivityViewModel.class.getSimpleName();

    public AddNoteActivityViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
    }

    public void saveNote() {
//
//        Log.d(TAG, "saveNote: title " + this.title);
//        Log.d(TAG, "saveNote: Description " + this.description);

        Note note = new Note(null, notesRepository.getCurrentUserId(), this.title, this.description, new ImageList(Arrays.asList()));
        notesRepository.addNote(note);
    }

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
}
