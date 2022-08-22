package com.homework.jdbc;

import com.homework.jdbc.dao.*;
import com.homework.jdbc.exception.NoSuchBookException;
import com.homework.jdbc.exception.NoSuchStudentException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException, NoSuchBookException, NoSuchStudentException {

        BooksDao booksDao = new BooksDaoImpl();
        // Get all books
        booksDao.readAllBooks().forEach(System.out::println);
        // Get book by id
        System.out.println(booksDao.readBooksById(UUID.fromString("117fa19f-5b14-461e-849c-e4546b21e891")));

        CourseDao courseDao = new CourseDaoImpl();
        // Add course samples
        //courseDao.addCourse("Java for beginners","6 months");
        //courseDao.addCourse("Java for advanced","9 months");
        // Get all courses
        courseDao.getAllCourse().forEach(System.out::println);

        StudentDao studentDao = new StudentDaoImpl();
        // Add student sample
        //studentDao.addStudent("Viktoriya","Markova", Date.valueOf("1988-11-23"),UUID.fromString("d6e4eb0f-6544-4ce7-90ad-adf79787d0cb"),UUID.fromString("749bd65b-4121-4d92-af17-41ef89ebce08"));
        // Get all students
        //studentDao.getAllStudents().forEach(System.out::println);
        System.out.println(studentDao.getStudentById(UUID.fromString("10b01954-33e9-4fc6-8ebc-0f8be6e8888e")));
    }
}
