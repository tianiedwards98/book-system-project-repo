package com.company.bookservice.service;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.dao.BookDaoJdbcTemplateImpl;
import com.company.bookservice.feign.NoteService;
import com.company.bookservice.models.Book;
import com.company.bookservice.models.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ServiceLayerTest {

    BookDao bookDao;

    ServiceLayer serviceLayer;

    @Mock
    NoteService noteService;

    List<Note> notes = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        setUpBookDaoMock();
        setUpNoteServiceMock();
        serviceLayer = new ServiceLayer(bookDao,noteService);

    }

    @Test
    public void shouldReturnNewBook(){
        BookViewModel book = new BookViewModel();
        book.setAuthor("Worm");
        book.setTitle("Book Worm");

        BookViewModel outputBook = new BookViewModel();
        outputBook.setTitle("Book Worm");
        outputBook.setAuthor("Worm");
        outputBook.setBookId(1);
        outputBook.setNotes(noteService.getNotesByBook(outputBook.getBookId()));

        book = serviceLayer.createBook(book);

        assertNotNull(book);


    }
    @Test
    public void shouldReturnBookById(){
        Book book = new Book();
        book.setAuthor("Worm");
        book.setTitle("Book Worm");

        BookViewModel outputBook = new BookViewModel();
        outputBook.setTitle("Book Worm");
        outputBook.setAuthor("Worm");
        outputBook.setBookId(1);
        outputBook.setNotes(noteService.getNotesByBook(outputBook.getBookId()));

        BookViewModel bookViewModel = serviceLayer.getBookById(outputBook.getBookId());

        assertEquals(outputBook,bookViewModel);

    }
    @Test
    public void shouldReturnAllBooks(){
        Book book = new Book();
        book.setTitle("Book Worm");
        book.setAuthor("Worm");

        Book book2 = new Book();
        book2.setTitle("Hello, World");
        book2.setAuthor("EARTH");

        Book book3 = new Book();
        book3.setTitle("I ruined 2020.");
        book3.setAuthor("COVID19");

        BookViewModel outputBook = new BookViewModel();
        outputBook.setBookId(1);
        outputBook.setTitle("Book Worm");
        outputBook.setAuthor("Worm");
        outputBook.setNotes(noteService.getNotesByBook(outputBook.getBookId()));


        BookViewModel outputBook2 = new BookViewModel();
        outputBook2.setBookId(2);
        outputBook2.setTitle("Hello, World");
        outputBook2.setAuthor("EARTH");
        outputBook2.setNotes(noteService.getNotesByBook(outputBook2.getBookId()));

        BookViewModel outputBook3 = new BookViewModel();
        outputBook3.setBookId(3);
        outputBook3.setTitle("I ruined 2020.");
        outputBook3.setAuthor("COVID19");
        outputBook3.setNotes(noteService.getNotesByBook(outputBook3.getBookId()));

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book2);
        bookList.add(book3);

        assertEquals(3,bookList.size());


    }


    private void setUpBookDaoMock() {
       bookDao = mock(BookDaoJdbcTemplateImpl.class);

        Book book = new Book();
        book.setTitle("Book Worm");
        book.setAuthor("Worm");

        Book book2 = new Book();
        book2.setTitle("Hello, World");
        book2.setAuthor("EARTH");

        Book book3 = new Book();
        book3.setTitle("I ruined 2020.");
        book3.setAuthor("COVID19");

        Book outputBook = new Book();
        outputBook.setBookId(1);
        outputBook.setTitle("Book Worm");
        outputBook.setAuthor("Worm");


        Book outputBook2 = new Book();
        outputBook2.setBookId(2);
        outputBook2.setTitle("Hello, World");
        outputBook2.setAuthor("EARTH");

        Book outputBook3 = new Book();
        outputBook3.setBookId(3);
        outputBook3.setTitle("I ruined 2020.");
        outputBook3.setAuthor("COVID19");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book2);
        bookList.add(book3);

        doReturn(outputBook).when(bookDao).createBook(book);
        doReturn(outputBook).when(bookDao).getBookById(1);



    }

    private void setUpNoteServiceMock() {

        Note note = new Note();
        note.setNoteId(1);
        note.setBookId(1);
        note.setNote("Heyo!");

        notes.add(note);

        Note note1 = new Note();
        note1.setNoteId(1);
        note1.setBookId(1);
        note1.setNote("Book Note 1");

        Note note2 = new Note();
        note2.setNoteId(2);
        note2.setBookId(1);
        note2.setNote("Book Note 2");

        Note note3 = new Note();
        note3.setNoteId(3);
        note1.setBookId(1);
        note1.setNote("Book Note 3");

        notes.add(note1);
        notes.add(note2);
        notes.add(note3);

        doReturn(notes).when(noteService).getNotesByBook(1);
        doReturn(notes).when(noteService).getNotesByBook(2);



    }
}