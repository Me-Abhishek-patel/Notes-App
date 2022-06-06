package com.ciberciti.notes.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.ciberciti.notes.R;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.databinding.RvListItemBinding;

import java.util.ArrayList;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    ArrayList<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_list_item, parent, false);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        if (!note.getImages().getImages().isEmpty())
            holder.iv.setImageBitmap(note.getImages().getImages().get(note.getImages().getImages().size()-1));
        else holder.iv.setImageResource(R.drawable.placeholder_image);
        holder.itemBinding.setNote(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        private RvListItemBinding itemBinding;

        public NoteViewHolder(@NonNull RvListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            iv = itemBinding.ivNoteItem;
            itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickPosition = getAdapterPosition();
                    if (listener != null && clickPosition != RecyclerView.NO_POSITION) {
                        listener.onItemClick(notes.get(clickPosition));
                    }
                }
            });
        }


    }


}