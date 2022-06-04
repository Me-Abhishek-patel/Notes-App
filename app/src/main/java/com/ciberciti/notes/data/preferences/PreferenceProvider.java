package com.ciberciti.notes.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.ciberciti.notes.utils.Constants.IS_USER_LOGGED_IN;
import static com.ciberciti.notes.utils.Constants.USER_ID;

public class PreferenceProvider {
    Context context;
    SharedPreferences sharedPreferences;

    public PreferenceProvider(Context context) {
        this.context = context.getApplicationContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void addSession(int userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_ID, userId);
        editor.putBoolean(IS_USER_LOGGED_IN, true);
        editor.apply();
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(IS_USER_LOGGED_IN, false);
    }

    public void removeSession() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USER_ID);
        editor.putBoolean(IS_USER_LOGGED_IN, false);
        editor.apply();
    }

    public int getCurrentUserId() {
        return sharedPreferences.getInt(USER_ID, 0);
    }

}
