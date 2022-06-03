package com.ciberciti.notes.repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.ciberciti.notes.data.dao.NoteDAO;
import com.ciberciti.notes.data.dao.UserDAO;
import com.ciberciti.notes.data.db.AppDatabase;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.data.entities.User;

import java.util.List;

public class NotesRepository {
    private NoteDAO noteDAO;
    private UserDAO userDAO;

    public NotesRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        noteDAO = appDatabase.noteDAO();
        userDAO = appDatabase.userDAO();
    }

    public LiveData<Note> getUserNotes(String userId) {
        return noteDAO.getUserNotes(userId);
    }

    public LiveData<List<Note>> getCurrentNote(String noteId) {
        return noteDAO.getNote(noteId);
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

        public InsertUserAsyncTask(UserDAO noteDAO) {
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


}
