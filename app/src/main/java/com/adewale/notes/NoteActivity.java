package com.adewale.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();
        editText = findViewById(R.id.note_edit_text);
         id = intent.getIntExtra("id",0);
        String content = intent.getStringExtra("contents");
        editText.setText(content);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.noteDatabase.noteDao().save(editText.getText().toString(), id);
    }


}