package com.ciberciti.notes.data.entities;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    public Integer userId;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String mobileNumber;

    @ColumnInfo
    public String email;

    @ColumnInfo
    public String password;

    public User() {
    }

    public User(Integer id, String name, String mobileNumber, String emails, String password) {
        this.userId = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = emails;
        this.password = password;
    }

    @Bindable
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
        notifyPropertyChanged(BR.userId);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        notifyPropertyChanged(BR.mobileNumber);

    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
