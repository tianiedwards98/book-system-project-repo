package com.company.bookservice.dao;

import com.company.bookservice.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements BookDao {
    private JdbcTemplate jdbcTemplate;

    //prepared Statements
    private static final String INSERT_NOTE_SQL =
            "insert into note (book_id, note) values ( ?, ?)";
    private static final String SELECT_NOTE_SQL =
            "select * from note where note_id = ?";
    private static final String SELECT_ALL_NOTE_SQL =
            "select * from note";
    private static final String DELETE_NOTE_SQL =
            "delete from note where note_id =?";
    private static final String UPDATE_NOTE_SQL =
            "update note set book_id = ?, note = ? where note_id = ?";
    private static final String SELECT_NOTES_BY_BOOK =
            "select * from note where book_id = ?";


    @Autowired
    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Book createBook(Book book) {
        return null;
    }

    @Override
    public Book getBookById(int id) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void deleteBook(int id) {

    }
    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException{
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));

        return book;
    }
}
