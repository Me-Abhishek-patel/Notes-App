package com.ciberciti.notes.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.ciberciti.notes.R;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.databinding.ActivityMainBinding;
import com.ciberciti.notes.ui.addnote.AddNoteActivity;
import com.ciberciti.notes.ui.auth.AuthActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    MainActivityViewModel mainActivityViewModel;
    TextView textView;
    private MainActivityClickHandlers mainActivityClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityClickHandlers = new MainActivityClickHandlers(this);
        binding.setMainActivityClickHandlers(mainActivityClickHandlers);
        setContentView(binding.getRoot());
        textView = findViewById(R.id.tvTestData);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        if (mainActivityViewModel.isUserLoggedIn())
            Log.d("hai main", "onCreate: " + mainActivityViewModel.getCurrentUserId());
        mainActivityViewModel.geUserNotes(mainActivityViewModel.getCurrentUserId()).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                StringBuilder sb = new StringBuilder();
                for (Note note : notes) {
                    sb.append("\ntitle: " + note.title);
                    sb.append("\nDescription: " + note.description);
                    sb.append("\n________________\n");

                }
                textView.setText(sb.toString());
            }
        });

        setSupportActionBar(binding.toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            mainActivityViewModel.logout();
            Intent intent = new Intent(MainActivity.this, AuthActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mainActivityViewModel.isUserLoggedIn()) {
            startLogin();
        }
    }

    private void startLogin() {
        Intent intent = new Intent(MainActivity.this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

    public class MainActivityClickHandlers {
        Context context;

        public MainActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onFabClicked(View view) {

            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);

        }
    }
}