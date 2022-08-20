package com.homework.jdbc.dao;

// CRUD

import com.homework.jdbc.entity.BookEntity;

import java.util.Date;
import java.util.List;

public interface BookDao {

    List<BookEntity> getAllBooks();

    void addBook(
            String bookName,
            String bookPublication,
            Date bookDatePublication,

            )
}
