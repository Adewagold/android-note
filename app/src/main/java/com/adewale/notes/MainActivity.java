package com.adewale.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.adewale.notes.adapter.NoteAdapter;
import com.adewale.notes.database.NoteDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoteAdapter notesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public  static NoteDatabase noteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //build database
        noteDatabase = Room.databaseBuilder(getApplicationContext(),NoteDatabase.class,"notes").allowMainThreadQueries().build();

        recyclerView = findViewById(R.id.note_list);
        layoutManager = new LinearLayoutManager(this);

        notesAdapter = new NoteAdapter();
        notesAdapter.reload();


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(notesAdapter);

        FloatingActionButton floatingButton = findViewById(R.id.add_note_button);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteDatabase.noteDao().Create();
                notesAdapter.reload();
            }
        });
    }

    protected void onResume(){
        super.onResume();
        notesAdapter.reload();
    }
}