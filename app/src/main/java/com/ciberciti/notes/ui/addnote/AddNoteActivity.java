package com.ciberciti.notes.ui.addnote;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ciberciti.notes.R;
import com.ciberciti.notes.data.entities.Note;
import com.ciberciti.notes.databinding.ActivityAddNoteBinding;
import com.ciberciti.notes.ui.recyclerview.ImageAdapter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static com.ciberciti.notes.utils.Constants.*;

public class AddNoteActivity extends AppCompatActivity {
    public String title;
    public String description;
    public AddNoteActivityViewModel addNoteActivityViewModel;
    ActivityAddNoteBinding binding;

    AddNoteActivityClickHandlers addNoteActivityClickHandlers;
    RecyclerView recyclerView;
    ImageAdapter imageAdapter;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        addNoteActivityViewModel = new ViewModelProvider(this).get(AddNoteActivityViewModel.class);
        Intent intent = getIntent();
        if (intent != null) {
            addNoteActivityViewModel.setNoteId(intent.getIntExtra(NOTE_ID, NOT_A_NOTE_ID));
            loadNote();
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);

        binding.setViewModel(addNoteActivityViewModel);
        addNoteActivityClickHandlers = new AddNoteActivityClickHandlers(this);
        binding.setClickHandler(addNoteActivityClickHandlers);
        recyclerView = binding.rvImagesAdd;


        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "MyPicture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        loadRecyclerView();


    }

    private void validateTextLength() {

        binding.tvNewNoteTitle.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                validate();
            }
        });
        binding.tvNewNoteDescription.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                validate();
            }
        });
    }

    private boolean validate() {
        if (addNoteActivityViewModel.getTitle() == null ||
                addNoteActivityViewModel.getTitle().length() < 5) {
            binding.tvNewNoteTitle.setError("Title must be at lest 5 characters long");
            return false;
        } else
            binding.tvNewNoteTitle.setError(null);

        if (addNoteActivityViewModel.getDescription() == null ||
                addNoteActivityViewModel.getDescription().length() < 100) {
            binding.tvNewNoteDescription.setError("Description must be at least 100 characters long");
            return false;
        } else
            binding.tvNewNoteDescription.setError(null);
        return true;
    }

    private void loadNote() {
        addNoteActivityViewModel.getNote().observe(this, new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                if (note != null) {
                    binding.tvNewNoteTitle.getEditText().setText(note.title);
                    binding.tvNewNoteDescription.getEditText().setText(note.description);
                    addNoteActivityViewModel.setTitle(note.getTitle());
                    addNoteActivityViewModel.setDescription(note.getDescription());
                    addNoteActivityViewModel.setImageLists((ArrayList<Bitmap>) note.getImages().getImages());
                    imageAdapter.setBitmaps((ArrayList<Bitmap>) note.getImages().getImages());
                    imageAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void loadRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageAdapter = new ImageAdapter();
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.setBitmaps(addNoteActivityViewModel.getImageLists());
    }

    private void saveNote() {

        if (validate()) {
            addNoteActivityViewModel.saveNote();
            Toast.makeText(AddNoteActivity.this, "Note Added", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            validateTextLength();
            saveNote();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case ACTION_CAMERA_CAPTURE:
                if (resultCode == RESULT_OK) {


//                    Bitmap selectedImage = (Bitmap) imageReturnedIntent.getExtras().get("Data");
//                    iv.setImageBitmap(selectedImage);
                    Bitmap bitmap;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                        addNoteActivityViewModel.appendBitmap(bitmap, displayMetrics.widthPixels / 2);
                        imageAdapter.notifyDataSetChanged();
                        if (addNoteActivityViewModel.getImageLists().size() >= 10)
                            binding.outlinedButton.setVisibility(View.GONE);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case ACTION_GALLERY_CAPTURE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    Bitmap bitmap;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                        addNoteActivityViewModel.appendBitmap(bitmap, displayMetrics.widthPixels / 2);
                        imageAdapter.notifyDataSetChanged();
                        if (addNoteActivityViewModel.getImageLists().size() >= 10)
                            binding.outlinedButton.setVisibility(View.GONE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                startActivityForResult(cameraIntent, ACTION_CAMERA_CAPTURE);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public class AddNoteActivityClickHandlers {
        Context context;

        public AddNoteActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onAddImageClicked(View view) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setMessage("Choose Image Source");
            alertDialogBuilder.setPositiveButton("Gallery",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, ACTION_GALLERY_CAPTURE);
                        }
                    });

            alertDialogBuilder.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                        } else {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                            startActivityForResult(cameraIntent, ACTION_CAMERA_CAPTURE);

                        }
                    }

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setCanceledOnTouchOutside(true);
            alertDialog.show();
        }
    }

}


