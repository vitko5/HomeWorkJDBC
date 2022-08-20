package com.homework.jdbc;

import com.homework.jdbc.dao.*;
import com.homework.jdbc.exception.NoSuchBookException;

import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException, NoSuchBookException {

        // Get all books
        //BooksDao booksDao = new BooksDaoImpl();
        //booksDao.readAllBooks().forEach(System.out::println);

        // Get book by id
        BooksDao booksDao = new BooksDaoImpl();
        System.out.println(booksDao.readBooksById(UUID.fromString("117fa19f-5b14-461e-849c-e4546b21e891")));


    }
}
