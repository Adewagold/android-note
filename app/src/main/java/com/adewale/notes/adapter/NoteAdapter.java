package com.adewale.notes.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adewale.notes.MainActivity;
import com.adewale.notes.NoteActivity;
import com.adewale.notes.R;
import com.adewale.notes.model.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    public static class NoteViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        LinearLayout containerView;

        public NoteViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.note_row_text);
            containerView = itemView.findViewById(R.id.note_row);


            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Note currentNote = (Note)containerView.getTag();
                    Intent intent = new Intent(v.getContext(), NoteActivity.class);
                    intent.putExtra("id", currentNote.id);
                    intent.putExtra("contents", currentNote.contents);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
    List<Note> notes = new ArrayList<>();
    //Binding ViewHolder

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textView.setText(currentNote.getContents());
        holder.containerView.setTag(currentNote);
    }

    //Fetching all

    //Create ViewHolder
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_row, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public void reload(){
        notes = MainActivity.noteDatabase.noteDao().getAllNotes();
        notifyDataSetChanged();
    }
}
