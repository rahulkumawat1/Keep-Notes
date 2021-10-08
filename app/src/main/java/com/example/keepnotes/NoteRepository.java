package com.example.keepnotes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    NoteRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAlphabetizedNotes();
    }


    LiveData<List<Note>> getAllWords() {
        return mAllNotes;
    }


    void insert(Note note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.insert(note);
        });
    }

    void delete(Note note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.delete(note);
        });
    }
}
