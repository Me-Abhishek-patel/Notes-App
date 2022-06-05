package com.ciberciti.notes.ui.notedetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.ciberciti.notes.R;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.databinding.ActivityNoteDetailsBinding;

import static com.ciberciti.notes.utils.Constants.NOTE_ID;
import static com.ciberciti.notes.utils.Constants.NOT_A_NOTE_ID;

public class NoteDetailsActivity extends AppCompatActivity {
    NotesDetailsActivityViewModel notesDetailsActivityViewModel;
    ActivityNoteDetailsBinding binding;
    String TAG = NoteDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        notesDetailsActivityViewModel = new ViewModelProvider(this).get(NotesDetailsActivityViewModel.class);
        Intent intent = getIntent();
        if (intent != null) {
            notesDetailsActivityViewModel.setNoteId(intent.getIntExtra(NOTE_ID, NOT_A_NOTE_ID));
            Log.d(TAG, "onCreate: " + intent.getIntExtra(NOTE_ID, NOT_A_NOTE_ID));
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_details);
        binding.setNoteDetailViewModel(notesDetailsActivityViewModel);

        loadNote();
    }

    private void loadNote() {
        notesDetailsActivityViewModel.getNote().observe(this, new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                if(note!=null) {
                    binding.tvNoteTitle.setText(note.title);
                    binding.tvNewNoteDescription.setText(note.description);
                    notesDetailsActivityViewModel.setTitle(note.getTitle());
                    notesDetailsActivityViewModel.setDescription(note.getDescription());
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
            notesDetailsActivityViewModel.deleteNote();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}