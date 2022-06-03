package com.ciberciti.notes.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.ciberciti.notes.data.entities.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT  * FROM User")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM User WHERE email = :email")
    LiveData<User> getUserWithEmail(String email);

    @Query("SELECT * FROM User WHERE mobileNumber = :mobile")
    LiveData<User> getUserWithMobile(String mobile);



}
