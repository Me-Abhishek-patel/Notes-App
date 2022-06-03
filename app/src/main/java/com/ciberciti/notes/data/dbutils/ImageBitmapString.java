package com.ciberciti.notes.data.dbutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

public class ImageBitmapString {


    @TypeConverter
    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    @TypeConverter
    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;

        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @TypeConverter
    public ImageList storedStringToImages(String value) {
        List<String> list = Arrays.asList(value.split("\\s*,\\s*"));
        return new ImageList(list);
    }

    @TypeConverter
    public String imagesToStoredString(ImageList imgeListWrapper) {

        StringBuilder value = new StringBuilder();

        for (String str : imgeListWrapper.getImages())
            value.append(str).append(",");

        return value.toString();
    }
}