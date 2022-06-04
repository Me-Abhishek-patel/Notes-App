package com.ciberciti.notes.ui.auth;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.ciberciti.notes.data.entities.User;
import com.ciberciti.notes.repository.NotesRepository;

public class AuthViewModel extends AndroidViewModel {
    public Integer userId = null;
    public String userName;
    public String userMobile;
    public String userEmail;
    public String password;
    NotesRepository repository;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        repository = new NotesRepository(application);
    }

    public LiveData<User> register() {
        User user = new User(null, userName, userMobile, userEmail, password);
        repository.addUser(user);
        return repository.getUserWithEmail(user.getEmail());

    }

    public boolean isUserLoggedIn() {
        return repository.isUserLoggedIn();
    }

    public void addSession(int userId) {
        repository.addSession(userId);
    }

    public LiveData<User> login() {
        return repository.getUserWithEmail(userEmail);
    }
}
