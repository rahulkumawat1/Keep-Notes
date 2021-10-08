package com.example.keepnotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Delete()
    void delete(Note note);

    @Query("SELECT * FROM notes_table ORDER BY note ASC")
    LiveData<List<Note>> getAlphabetizedNotes();

    @Query("DELETE FROM notes_table")
    void deleteAll();

}