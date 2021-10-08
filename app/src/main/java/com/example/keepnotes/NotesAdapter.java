package com.example.keepnotes;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> notesArray;
    private NoteClicked listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteText;
        private final ImageView deleteButton;

        public ViewHolder(View view) {
            super(view);
            noteText = (TextView) view.findViewById(R.id.noteText);
            deleteButton = (ImageView) view.findViewById(R.id.deleteButton);
        }

    }

    public NotesAdapter(NoteClicked listener) {
        notesArray = new ArrayList<Note>();
        this.listener = listener;
    }

    public void update(List<Note> notesArray){
        this.notesArray.clear();
        this.notesArray.addAll(notesArray);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.deleteButton.setOnClickListener(v ->
                listener.ondeleteItem(notesArray.get(viewHolder.getAdapterPosition())));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.noteText.setText(notesArray.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return notesArray.size();
    }
}

interface NoteClicked
{
    void ondeleteItem(Note note);
}
