package com.example.keepnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NoteClicked {

    private NotesAdapter adapter;
    private NoteViewModel mNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyleView = findViewById(R.id.recyclerview);
        recyleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesAdapter(this);
        recyleView.setAdapter(adapter);

        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mNoteViewModel.getAllNotes().observe(this, notes -> {
            adapter.update(notes);
        });
    }


    @Override
    public void ondeleteItem(Note note) {
        mNoteViewModel.delete(note);
    }

    public void submitNote(View view) {
        EditText note = findViewById(R.id.editNote);
        String noteText = note.getText().toString();
        note.setText("");
        if (noteText.isEmpty()) {
            Toast.makeText(this, "Please Enter Text", Toast.LENGTH_SHORT).show();
            return;
        }
        mNoteViewModel.insert(new Note(noteText));
    }
}