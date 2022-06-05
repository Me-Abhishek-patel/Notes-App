package com.ciberciti.notes.data.dbutils;

import android.graphics.Bitmap;

import java.util.List;

public class ImageList {
    List<Bitmap> images;

    public ImageList(List<Bitmap> al) {
        this.images = al;
    }

    public List<Bitmap> getImages() {
        return images;
    }

    public void setImages(List<Bitmap> images) {
        this.images = images;
    }
}
