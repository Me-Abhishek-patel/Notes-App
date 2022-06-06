package com.ciberciti.notes.ui.recyclerview;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.ciberciti.notes.R;
import com.ciberciti.notes.databinding.ImageItemBinding;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageItemViewHolder> {
    ArrayList<Bitmap> bitmaps = new ArrayList<>();


    public void setBitmaps(ArrayList<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.image_item, parent, false);
        return new ImageItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageItemViewHolder holder, int position) {
        Bitmap bitmap = bitmaps.get(position);
        holder.imageItemBinding.ivNoteItem.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return bitmaps.size();
    }

    class ImageItemViewHolder extends RecyclerView.ViewHolder {
        private ImageItemBinding imageItemBinding;

        public ImageItemViewHolder(@NonNull ImageItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.imageItemBinding = itemBinding;
        }
    }
}
