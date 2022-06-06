package com.ciberciti.notes.ui.auth;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.ciberciti.notes.data.entities.User;
import com.ciberciti.notes.repository.NotesRepository;
import com.ciberciti.notes.utils.InputValidator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class AuthViewModel extends AndroidViewModel {
    public Integer userId = null;
    public String userName;
    public String userMobile;
    public String userEmail;
    public String password;
    NotesRepository repository;
    public MutableLiveData<String> switcherText = new MutableLiveData<>();
    InputValidator validator;
    int activeFragmentId = 0;


    public AuthViewModel(@NonNull Application application) {
        super(application);
        switcherText.setValue("");
        repository = new NotesRepository(application);
        validator = new InputValidator();
    }

    public int getActiveFragmentId() {
        return activeFragmentId;
    }

    public void setActiveFragmentId(int activeFragmentId) {
        this.activeFragmentId = activeFragmentId;
    }

    public InputValidator getValidator() {
        return validator;
    }

    public void setValidator(InputValidator validator) {
        this.validator = validator;
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();


            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean isUserLoggedIn() {
        return repository.isUserLoggedIn();
    }

    public void addSession(int userId) {
        repository.addSession(userId);
    }

    public LiveData<User> loginWithEmail() {
        return repository.getUserWithEmail(userEmail);
    }

    public LiveData<User> loginWithMobile() {
        return repository.getUserWithMobileNumber(userEmail);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MutableLiveData<String> getSwitcherText() {
        return switcherText;
    }

    public void setSwitcherText(String text) {
        this.switcherText.setValue(text);
    }

    public LiveData<User> register() {
        User user = new User(null, userName, userMobile, userEmail, md5(password));
        repository.addUser(user);
        return repository.getUserWithEmail(user.getEmail());

    }
}
