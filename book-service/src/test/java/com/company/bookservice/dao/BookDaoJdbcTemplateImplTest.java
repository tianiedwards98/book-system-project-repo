package com.company.bookservice.dao;

import com.company.bookservice.models.Book;
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
public class BookDaoJdbcTemplateImplTest {
    @Autowired
    private BookDao bookDao;

    @Before
    public void setUp() throws Exception {
        List<Book> books = bookDao.getAllBooks();

        books.stream()
                .forEach(book -> bookDao.deleteBook(book.getBookId()));
    }

    @Test
    public void addGetDeleteBook(){
        Book book = new Book();
        book.setTitle("Book Worm");
        book.setAuthor("Worm");

        book = bookDao.createBook(book);

        Book book2 = bookDao.getBookById(book.getBookId());

        assertEquals(book, book2);

        bookDao.deleteBook(book.getBookId());

        book2 = bookDao.getBookById(book.getBookId());

        assertNull(book2);
    }
}