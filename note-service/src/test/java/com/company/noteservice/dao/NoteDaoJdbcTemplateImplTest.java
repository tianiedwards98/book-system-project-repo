package com.company.noteservice.dao;

import com.company.noteservice.models.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoJdbcTemplateImplTest {

    @Autowired
    private NoteDao noteDao;

    @Before
    public void setUp() throws Exception {
        List<Note> notes = noteDao.getAllNotes();

        notes.stream()
                .forEach(note -> noteDao.deleteNotes(note.getNoteId()));
    }

    @Test
    public void addGetDeleteNote() {
        Note note =new Note();
        note.setBookId(1);
        note.setNote("notebook");

        note = noteDao.createNote(note);

        Note note2 = noteDao.getNote(note.getNoteId());

        assertEquals(note,note2);

        noteDao.deleteNotes(note.getNoteId());

        note2 = noteDao.getNote(note.getNoteId());

        assertNull(note2);
    }

    @Test
    public void getNotesByBook() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("notebook");

        noteDao.createNote(note);

        note = new Note();
        note.setBookId(2);
        note.setNote("grocery list");

        noteDao.createNote(note);

        note = new Note();
        note.setBookId(2);
        note.setNote("shopping list");

        noteDao.createNote(note);

        List<Note> notes = noteDao.getNotesByBook(1);
        assertEquals(1,notes.size());

        notes = noteDao.getNotesByBook(2);
        assertEquals(2,notes.size());

        notes = noteDao.getNotesByBook(3);
        assertEquals(0,notes.size());
    }

    @Test
    public void getAllNotes() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("notebook");

         noteDao.createNote(note);

        note = new Note();
        note.setBookId(2);
        note.setNote("grocery list");

        noteDao.createNote(note);

        List<Note> notes = noteDao.getAllNotes();

        assertEquals(2,notes.size());
    }

    @Test
    public void updateNotes() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("notebook");

        noteDao.createNote(note);

        note.setBookId(1);
        note.setNote("grocery list");

        noteDao.updateNotes(note);

        Note note2 = noteDao.getNote(note.getNoteId());

        assertEquals(note2,note);
    }

}