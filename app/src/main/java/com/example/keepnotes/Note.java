package com.example.keepnotes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {

    @NonNull
    @ColumnInfo(name = "note")
    private String mNote;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Note(@NonNull String note) {
        this.mNote = note;
    }

    public String getNote() {
        return mNote;
    }
}
