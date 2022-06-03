package com.ciberciti.notes.data.entities;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import com.ciberciti.notes.data.dbutils.ImageList;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "owner", onDelete = CASCADE))
public class Note extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    public Integer noteId;

    @ColumnInfo
    public Integer owner;

    @ColumnInfo
    public String title;

    @ColumnInfo
    public String description;

    @ColumnInfo
    public ImageList images;


    public Note() {

    }

    public Note(Integer id, Integer owner, String title, String description, ImageList images) {
        this.noteId = id;
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.images = images;
    }

    @Bindable
    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
        notifyPropertyChanged(BR.noteId);
    }

    @Bindable
    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
        notifyPropertyChanged(BR.owner);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;

    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public ImageList getImages() {
        return images;
    }

    public void setImages(ImageList images) {
        this.images = images;
        notifyPropertyChanged(BR.images);
    }
}
