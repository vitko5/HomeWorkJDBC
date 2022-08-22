package com.homework.jdbc.dao;

import com.homework.jdbc.entity.BooksEntity;
import com.homework.jdbc.exception.NoSuchBookException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface BooksDao {
    void addBook(String bookName, int bookSize, String bookISBN, String bookAuthor, Date bookDatePublication) throws SQLException;
    List<BooksEntity> readAllBooks() throws SQLException;
    BooksEntity readBooksById(UUID bookId) throws SQLException, NoSuchBookException;
    void updateBook (UUID bookId, String bookName, int bookSize, String bookISBN, String bookAuthor, Date bookDatePublication);
}
