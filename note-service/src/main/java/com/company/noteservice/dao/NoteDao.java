package com.company.noteservice.dao;

import com.company.noteservice.models.Note;

import java.util.List;

public interface NoteDao {
    Note createNote(Note note);

    Note getNote(int id);

    List<Note> getNotesByBook(int bookId);

    List<Note> getAllNotes();

    void updateNotes(Note note);

    void deleteNotes(int id);
}
