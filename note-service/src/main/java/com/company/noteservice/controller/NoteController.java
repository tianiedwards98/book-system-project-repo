package com.company.noteservice.controller;

import com.company.noteservice.dao.NoteDao;
import com.company.noteservice.models.Note;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class NoteController {

    private NoteDao dao;

    //Create Note
    //===========
    //URI: /notes
    //HTTP Method: POST
    //RequestBody: Note data (minus ID)
    //ResponseBody: Note data (plus autogenerate ID)
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note){
        return dao.createNote(note);
    }
    //Get Note
    //========
    //URI: /notes/{id}
    //HTTP Method: GET
    //RequestBody: None
    //ResponseBody: Note data
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Note getNoteById(@RequestBody @PathVariable int id){
        return dao.getNote(id);
    }
    //Get Notes by Book
    //=================
    //URI: /notes/book/{book_id}
    //HTTP Method: GET
    //RequestBody: None
    //ResponseBody: Array of Note data
    @RequestMapping(value = "/notes/book/{bookId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getNotesByBook(@RequestBody @PathVariable int bookId){
        return dao.getNotesByBook(bookId);
    }
    //Get all Notes
    //=============
    //URI: /notes
    //HTTP Method: GET
    //RequestBody: None
    //ResponseBody: Array of Note data
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotes(){
        return dao.getAllNotes();
    }
    //Update Note
    //=================
    //URI: /notes/{id}
    //HTTP Method: PUT
    //RequestBody: Note data
    //ResponseBody: None
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNotes(@PathVariable int id, @RequestBody Note note){
        note.setNoteId(id);
        dao.updateNotes(note);
    }
    //Delete Note
    //===========
    //URI: /notes/{id}
    //HTTP Method: DELETE
    //RequestBody: None
    //ResponseBody: None
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotes(@PathVariable int id){
        dao.deleteNotes(id);
    }
}
