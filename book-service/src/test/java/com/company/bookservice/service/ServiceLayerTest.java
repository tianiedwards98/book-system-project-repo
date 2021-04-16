package com.company.bookservice.service;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.dao.BookDaoJdbcTemplateImpl;
import com.company.bookservice.feign.NoteService;
import com.company.bookservice.models.Book;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    BookDao bookDao;

    ServiceLayer serviceLayer;

    NoteService noteService;


    @Before
    public void setUp() throws Exception {
        setUpBookDaoMock();
        setUpNoteServiceMock();

        serviceLayer = new ServiceLayer(bookDao,noteService);
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
        doReturn(outputBook2).when(bookDao).createBook(book2);
        doReturn(outputBook3).when(bookDao).createBook(book3);
        doReturn(outputBook).when(bookDao).getBookById(1);
        doReturn(outputBook2).when(bookDao).getBookById(2);
        doReturn(outputBook3).when(bookDao).getBookById(3);
        doReturn(bookList).when(bookDao).getAllBooks();


    }

    private void setUpNoteServiceMock() {
        noteService  =mock(NoteService.class);


    }
}