package com.ciberciti.notes.data.dbutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageBitmapString {


    @TypeConverter
    public static String BitMapToString(Bitmap bitmap) {
        if (bitmap == null) return "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
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
        List<Bitmap> list = new ArrayList<>();
        for (String s : value.split("\\s*,\\s*")) {
            Bitmap btmp = StringToBitMap(s);
            list.add(btmp);
        }


        return new ImageList(list);
    }

    @TypeConverter
    public String imagesToStoredString(ImageList imgeListWrapper) {

        StringBuilder value = new StringBuilder();

        for (Bitmap btmp : imgeListWrapper.getImages()) {
            String str = BitMapToString(btmp);
            value.append(str).append(",");
        }

        return value.toString();
    }
}