package com.ciberciti.notes.data.db;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.ciberciti.notes.data.dao.NoteDAO;
import com.ciberciti.notes.data.dao.UserDAO;
import com.ciberciti.notes.data.dbutils.ImageBitmapString;
import com.ciberciti.notes.data.dbutils.ImageList;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.data.entities.User;

import java.util.Arrays;

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
            userDAO.insert(new User(null, "Abhi", "9999966", "email@email.com", "mypass"));
            userDAO.insert(new User(null, "Ashu", "9992439966", "emaisdfl@eyashoomail.com", "dfgsghag"));
            userDAO.insert(new User(null, "Abhi", "9999966", "email@email.com", "mypass"));
            userDAO.insert(new User(null, "Ashu", "9992439966", "emaisdfl@eyashoomail.com", "dfgsghag"));
            userDAO.insert(new User(null, "Abhi", "9999966", "email@email.com", "mypass"));
            userDAO.insert(new User(null, "Ashu", "9992439966", "emaisdfl@eyashoomail.com", "dfgsghag"));
            noteDAO.insert(new Note(null, 1, "Note 1", "Descriptions simply dummy text of the printing and typ", new ImageList(Arrays.asList())));
            noteDAO.insert(new Note(null, 1, "Note 2", "lorem50 simply dummy text of the",  new ImageList(Arrays.asList())));
            noteDAO.insert(new Note(null, 1, "Note 3", "Lorem Ipsum esetting industry. Lorem Ipsum ",  new ImageList(Arrays.asList())));
            noteDAO.insert(new Note(null, 1, "Note 4", "publishing software like Aldus PageMaker including versions of Lorem Ips",  new ImageList(Arrays.asList())));
            return null;
        }
    }
}
