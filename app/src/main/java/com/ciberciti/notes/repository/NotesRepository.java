package com.ciberciti.notes.repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.ciberciti.notes.data.dao.NoteDAO;
import com.ciberciti.notes.data.dao.UserDAO;
import com.ciberciti.notes.data.db.AppDatabase;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.data.entities.User;
import com.ciberciti.notes.data.preferences.PreferenceProvider;

import java.util.List;

public class NotesRepository {
    private NoteDAO noteDAO;
    private UserDAO userDAO;
    private PreferenceProvider preferenceProvider;
    public NotesRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        preferenceProvider = new PreferenceProvider(application);
        noteDAO = appDatabase.noteDAO();
        userDAO = appDatabase.userDAO();
    }

    public LiveData<List<Note>> getUserNotes(int userId) {
        return noteDAO.getUserNotes(userId);
    }

    public LiveData<Note> getCurrentNote(int noteId) {
        return noteDAO.getNote(noteId);
    }

    public LiveData<List<Note>> getAllNotes() {
        return noteDAO.getAllNotes();
    }


    public LiveData<User> getUserWithEmail(String email) {
        return userDAO.getUserWithEmail(email);
    }

    public LiveData<User> getUserWithMobileNumber(String mobileNumber) {
        return userDAO.getUserWithMobile(mobileNumber);
    }


    public void addNote(Note note) {
        new InsertNoteAsyncTask(noteDAO).execute(note);
    }

    public void addUser(User user) {
        new InsertUserAsyncTask(userDAO).execute(user);
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        public InsertUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {

            userDAO.insert(users[0]);
            return null;
        }
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDAO;

        public InsertNoteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDAO.insert(notes[0]);
            return null;
        }
    }

    public boolean isUserLoggedIn() {
        return preferenceProvider.isUserLoggedIn();
    }

    public int getCurrentUserId() {
        return preferenceProvider.getCurrentUserId();
    }

    public void addSession(int userId) {
        preferenceProvider.addSession(userId);
    }

    public void removeSession() {
        preferenceProvider.removeSession();
    }


}
