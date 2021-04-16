package com.company.bookservice.dao;

import com.company.bookservice.models.Book;

import java.util.List;

public interface BookDao {
    Book createBook(Book book);

    Book getBookById(int id);

    List<Book> getAllBooks();

    void updateBook(Book book);

    void deleteBook(int id);
}
