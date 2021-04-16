package com.company.bookservice.service;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.feign.NoteService;
import com.company.bookservice.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ServiceLayer {

    BookDao bookDao;

    NoteService noteService;
    @Autowired
    public ServiceLayer(BookDao bookDao,NoteService noteService){
        this.bookDao = bookDao;
    }

    private BookViewModel buildBookInvoiceViewModel(Book book ){
        BookViewModel bookViewModel = new BookViewModel();

        bookViewModel.setBookId(book.getBookId());
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthor(book.getAuthor());
        bookViewModel.setNotes(noteService.getAllNotes());

        return bookViewModel;

    }
    public BookViewModel createBook(Book book){
        return null;
    }
    public BookViewModel getBookById(int id){
        return null;
    }
    public List<BookViewModel> getAllBooks(){
        return null;
    }
    public void updateBook( Book book){

    }
    public void deleteBook(int id){

    }

}
