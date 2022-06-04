package com.ciberciti.notes.ui.addnote;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.ciberciti.notes.R;
import com.ciberciti.notes.databinding.ActivityAddNoteBinding;

public class AddNoteActivity extends AppCompatActivity {
    public String title;
    public String description;
    public AddNoteActivityViewModel addNoteActivityViewModel;
    ActivityAddNoteBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        addNoteActivityViewModel = new ViewModelProvider(this).get(AddNoteActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);
        binding.setViewModel(addNoteActivityViewModel);

    }

    private void saveNote() {
        addNoteActivityViewModel.saveNote();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            saveNote();
            Toast.makeText(AddNoteActivity.this, "Note Added", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}