package com.adewale.notes.repository;

import com.adewale.notes.model.Note;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface NoteDao {
    @Query("INSERT INTO notes (contents) VALUES ('New note')")
    void Create();

    @Query("SELECT * FROM notes")
    List<Note> getAllNotes();

    @Query("UPDATE notes SET contents=:contents WHERE id= :id")
    void save(String contents, int id);

}
