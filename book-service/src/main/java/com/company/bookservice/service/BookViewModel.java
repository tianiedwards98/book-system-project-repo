package com.company.bookservice.service;

import com.company.bookservice.models.Note;

import java.util.List;
import java.util.Objects;

public class BookViewModel {
    private int bookId;
    private String title;
    private String author;
    private List<Note> notes;

    public BookViewModel() {
    }

    public BookViewModel(int bookId, String title, String author, List<Note> notes) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.notes = notes;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel that = (BookViewModel) o;
        return bookId == that.bookId && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, notes);
    }

    @Override
    public String toString() {
        return "BookViewModel{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", notes=" + notes +
                '}';
    }
}
