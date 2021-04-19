package com.company.bookservice.service;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.feign.NoteService;
import com.company.bookservice.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    BookDao bookDao;

    NoteService noteService;

    @Autowired
    public ServiceLayer(BookDao bookDao,NoteService noteService){
        this.bookDao = bookDao;
        this.noteService = noteService;
    }

    private BookViewModel buildBookViewModel(Book book ){
        BookViewModel bookViewModel = new BookViewModel();

        bookViewModel.setBookId(book.getBookId());
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthor(book.getAuthor());
        bookViewModel.setNotes(noteService.getAllNotes());

        return bookViewModel;

    }
    public BookViewModel createBook(BookViewModel bookViewModel){
        Book book = new Book();
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
        book = bookDao.createBook(book);

        bookViewModel.setBookId(book.getBookId());
        bookViewModel.setNotes(noteService.getAllNotes());

        return bookViewModel;

    }
    public BookViewModel getBookById(int id){

        Book book = bookDao.getBookById(id);
        BookViewModel bvm = new BookViewModel();

        bvm.setBookId(book.getBookId());
        bvm.setAuthor(book.getAuthor());
        bvm.setTitle(book.getTitle());
        bvm.setNotes(noteService.getAllNotes());
        return bvm;
    }
    public List<BookViewModel> getAllBooks(){
        List<Book> books = bookDao.getAllBooks();
        List<BookViewModel> bookViewModels = new ArrayList<>();

        for(Book b: books){
            bookViewModels.add(buildBookViewModel(b));
        }
        return bookViewModels;
    }
    public void updateBook( BookViewModel bookViewModel){
        Book book = new Book();
        book.setBookId(bookViewModel.getBookId());
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());

        bookDao.updateBook(book);
    }
    public void deleteBook(int id){
        bookDao.deleteBook(id);
    }

}
