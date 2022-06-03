package com.ciberciti.notes.data.dbutils;

import java.util.List;

public class ImageList {
    List<String> images;

    public ImageList(List<String> al) {
        this.images = al;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
