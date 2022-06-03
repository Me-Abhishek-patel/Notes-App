package com.ciberciti.notes.data.db;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.ciberciti.notes.data.dbutils.ImageBitmapString;
import com.ciberciti.notes.data.dao.NoteDAO;
import com.ciberciti.notes.data.dao.UserDAO;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.data.entities.User;

@Database(entities = {User.class, Note.class}, version = 1)
@TypeConverters({ImageBitmapString.class})
public abstract class AppDatabase extends RoomDatabase {
    static AppDatabase instance;
    static RoomDatabase.Callback AppDBCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            InitialDataAsyncTask initialDataAsyncTask = new InitialDataAsyncTask();
            initialDataAsyncTask.execute();

        }
    };

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .addCallback(AppDBCallback)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDAO userDAO();

    public abstract NoteDAO noteDAO();

    public static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDAO userDAO;
        private NoteDAO noteDAO;

        @Override
        protected Void doInBackground(Void... voids) {
            this.userDAO = instance.userDAO();
            this.noteDAO = instance.noteDAO();
            userDAO.Insert(new User(null, "Abhi", "9999966", "email@email.com", "mypass"));
            userDAO.Insert(new User(null, "Ashu", "9992439966", "emaisdfl@eyashoomail.com", "dfgsghag"));
            userDAO.Insert(new User(null, "Abhi", "9999966", "email@email.com", "mypass"));
            userDAO.Insert(new User(null, "Ashu", "9992439966", "emaisdfl@eyashoomail.com", "dfgsghag"));
            userDAO.Insert(new User(null, "Abhi", "9999966", "email@email.com", "mypass"));
            userDAO.Insert(new User(null, "Ashu", "9992439966", "emaisdfl@eyashoomail.com", "dfgsghag"));
            return null;
        }
    }
}
