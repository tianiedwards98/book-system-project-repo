package com.company.bookservice.controller;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.models.Book;
import com.company.bookservice.service.BookViewModel;
import com.company.bookservice.service.ServiceLayer;
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
@WebMvcTest(BookServiceController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class BookServiceControllerTest {
    @MockBean
    private ServiceLayer serviceLayer;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldReturnAllBooksInCollection() throws Exception {
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

        BookViewModel outputBook2 = new BookViewModel();
        outputBook2.setBookId(2);
        outputBook2.setTitle("Hello, World");
        outputBook2.setAuthor("EARTH");

        BookViewModel outputBook3 = new BookViewModel();
        outputBook3.setBookId(3);
        outputBook3.setTitle("I ruined 2020.");
        outputBook3.setAuthor("COVID19");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book2);
        bookList.add(book3);

        when(serviceLayer.getAllBooks()).thenReturn(bookList);

        List<Book> listChecker = new ArrayList<>();
        listChecker.addAll(bookList);
        String outputJson = mapper.writeValueAsString(listChecker);

        // ACT
        this.mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
        // ASSERT (status code is 200)

    }

    @Test
    public void shouldReturnNewBookOnPostRequest() throws Exception {
        Book book3 = new Book();
        book3.setTitle("I ruined 2020.");
        book3.setAuthor("COVID19");

        String inputJson = mapper.writeValueAsString(book3);

        BookViewModel outputBook3 = new BookViewModel();
        outputBook3.setBookId(3);
        outputBook3.setTitle("I ruined 2020.");
        outputBook3.setAuthor("COVID19");
        String outputJson = mapper.writeValueAsString(outputBook3);

        when(serviceLayer.createBook(book3)).thenReturn(outputBook3);

        mockMvc.perform(
                post("/books")                                // perform the POST request
                        .content(inputJson)                         // Set the request body
                        .contentType(MediaType.APPLICATION_JSON)    // Tell the server it's in JSON format
        )
                .andDo(print())                                     // print results to console
                .andExpect(status().isCreated())                    // ASSERT (status code is 201)
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldReturnBookById() throws Exception{
        Book outputBook3 = new Book();
        outputBook3.setBookId(3);
        outputBook3.setTitle("I ruined 2020.");
        outputBook3.setAuthor("COVID19");

        String outputJson = mapper.writeValueAsString(outputBook3);

        when(serviceLayer.getBookById(3)).thenReturn(outputBook3);


        mockMvc.perform(get("/books/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        Book outputBook3 = new Book();
        outputBook3.setBookId(3);
        outputBook3.setTitle("I ruined 2020.");
        outputBook3.setAuthor("COVID19");

        String inputJson = mapper.writeValueAsString(outputBook3);


        mockMvc.perform(
                put("/books/3")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content
        mockMvc.perform(delete("/books/3"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}