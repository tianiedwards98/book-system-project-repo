package com.company.noteservice.dao;

import com.company.noteservice.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NoteDaoJdbcTemplateImpl implements NoteDao{
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
   public NoteDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
       this.jdbcTemplate = jdbcTemplate;
   }

    @Override
    public Note createNote(Note note) {
       jdbcTemplate.update(
               INSERT_NOTE_SQL,
               note.getBookId(),
               note.getNote()
       );
       int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

       note.setNoteId(id);

        return note;
    }

    @Override
    public Note getNote(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_NOTE_SQL, this::mapRowToNote, id);

        } catch (EmptyResultDataAccessException e) {
            // if nothing is returned just catch the exception and return null
            return null;
        }
    }

    @Override
    public List<Note> getNotesByBook(int bookId) {
        return jdbcTemplate.query(SELECT_NOTES_BY_BOOK, this::mapRowToNote, bookId);
    }

    @Override
    public List<Note> getAllNotes() {
        return jdbcTemplate.query(SELECT_ALL_NOTE_SQL, this::mapRowToNote);
    }

    @Override
    public void updateNotes(Note note) {
        jdbcTemplate.update(
                UPDATE_NOTE_SQL,
                note.getBookId(),
                note.getNote(),
                note.getNoteId()

        );
    }

    @Override
    public void deleteNotes(int id) {
        jdbcTemplate.update(DELETE_NOTE_SQL, id);
    }

    private Note mapRowToNote(ResultSet rs, int rowNum) throws SQLException{
       Note note = new Note();
       note.setNoteId(rs.getInt("note_id"));
       note.setBookId(rs.getInt("book_id"));
       note.setNote(rs.getString("note"));

       return note;
    }
}
