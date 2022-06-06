package com.ciberciti.notes.ui.notedetails;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ciberciti.notes.R;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.databinding.ActivityNoteDetailsBinding;
import com.ciberciti.notes.ui.addnote.AddNoteActivity;
import com.ciberciti.notes.ui.recyclerview.ImageAdapter;

import java.util.ArrayList;

import static com.ciberciti.notes.utils.Constants.NOTE_ID;
import static com.ciberciti.notes.utils.Constants.NOT_A_NOTE_ID;

public class NoteDetailsActivity extends AppCompatActivity {
    NotesDetailsActivityViewModel notesDetailsActivityViewModel;
    ActivityNoteDetailsBinding binding;
    String TAG = NoteDetailsActivity.class.getSimpleName();

    ArrayList<Bitmap> bitmaps;
    RecyclerView recyclerView;
    ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        notesDetailsActivityViewModel = new ViewModelProvider(this).get(NotesDetailsActivityViewModel.class);
        Intent intent = getIntent();
        if (intent != null) {
            notesDetailsActivityViewModel.setNoteId(intent.getIntExtra(NOTE_ID, NOT_A_NOTE_ID));
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_details);
        binding.setNoteDetailViewModel(notesDetailsActivityViewModel);
        recyclerView = binding.rvImagesDetails;
        loadRecyclerView();
        loadNote();

    }

    private void loadRecyclerView() {
        bitmaps = notesDetailsActivityViewModel.getImageLists();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageAdapter = new ImageAdapter();
        imageAdapter.setBitmaps(bitmaps);
        recyclerView.setAdapter(imageAdapter);

    }

    private void loadNote() {
        notesDetailsActivityViewModel.getNote().observe(this, new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                if (note != null) {
                    binding.tvNoteTitle.setText(note.title);
                    binding.tvNewNoteDescription.setText(note.description);
                    notesDetailsActivityViewModel.setTitle(note.getTitle());
                    notesDetailsActivityViewModel.setDescription(note.getDescription());
                    notesDetailsActivityViewModel.setImageLists((ArrayList<Bitmap>) note.getImages().getImages());
                    imageAdapter.setBitmaps((ArrayList<Bitmap>) note.getImages().getImages());
                    imageAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete) {
            new AlertDialog.Builder(this)
                    .setTitle("DELETE")
                    .setMessage("Do you really want to delete?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            notesDetailsActivityViewModel.deleteNote();
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();

            return true;
        }
        if (id == R.id.action_edit) {
            Intent intent = new Intent(this, AddNoteActivity.class);
            intent.putExtra(NOTE_ID, notesDetailsActivityViewModel.getNoteId());
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}