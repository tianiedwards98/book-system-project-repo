package com.company.noteservice.controller;

import com.company.noteservice.dao.NoteDao;
import com.company.noteservice.models.Note;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class NoteControllerTest {
    @MockBean
    private NoteDao dao;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

    }
    @Test
    public void shouldReturnAllNotesInCollection() throws Exception {

        // ARRANGE
        Note note = new Note();
        note.setBookId(1);
        note.setNote("NOTE");

        Note note2 = new Note();
        note2.setBookId(2);
        note2.setNote("NOTE");

        Note note3 = new Note();
        note3.setBookId(3);
        note3.setNote("NOTE");

        Note outputNote = new Note();
        outputNote.setBookId(1);
        outputNote.setNote("NOTE");
        outputNote.setNoteId(1);

        Note outputNote2 = new Note();
        outputNote2.setBookId(2);
        outputNote2.setNote("NOTE");
        outputNote2.setNoteId(2);

        Note outputNote3 = new Note();
        outputNote3.setBookId(3);
        outputNote3.setNote("NOTE");
        outputNote3.setNoteId(3);

        List<Note> noteList = new ArrayList<>();
        noteList.add(note);
        noteList.add(note2);
        noteList.add(note3);

        when(dao.getAllNotes()).thenReturn(noteList);

        List<Note> listChecker = new ArrayList<>();
        listChecker.addAll(noteList);

        String outputJson = mapper.writeValueAsString(listChecker);

        // ACT
        this.mockMvc.perform(get("/notes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
        // ASSERT (status code is 200)
    }

    @Test
    public void shouldReturnNewNoteOnPostRequest() throws Exception {

        // ARRANGE
        Note inputNote = new Note();
        inputNote.setBookId(1);
        inputNote.setNote("notebook");
        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(inputNote);

        Note outputNote = new Note();
        outputNote.setBookId(2);
        outputNote.setNote("The River");
        outputNote.setNoteId(6);

        String outputJson = mapper.writeValueAsString(outputNote);

        when(dao.createNote(inputNote)).thenReturn(outputNote);

        // ACT
        mockMvc.perform(
                post("/notes")                                // perform the POST request
                        .content(inputJson)                         // Set the request body
                        .contentType(MediaType.APPLICATION_JSON)    // Tell the server it's in JSON format
        )
                .andDo(print())                                     // print results to console
                .andExpect(status().isCreated())                    // ASSERT (status code is 201)
                .andExpect(content().json(outputJson));             // ASSERT (output is as expected)
    }

    @Test
    public void shouldReturnRecordById() throws Exception {
        Note outputNote = new Note();
        outputNote.setBookId(2);
        outputNote.setNote("The River");
        outputNote.setNoteId(6);

        String outputJson = mapper.writeValueAsString(outputNote);

        when(dao.getNote(6)).thenReturn(outputNote);

        mockMvc.perform(get("/notes/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        Note inputNote = new Note();
        inputNote.setBookId(1);
        inputNote.setNote("notebook");
        inputNote.setNoteId(6);

        String inputJson = mapper.writeValueAsString(inputNote);


        mockMvc.perform(
                put("/notes/6")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content
        mockMvc.perform(delete("/notes/6"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}