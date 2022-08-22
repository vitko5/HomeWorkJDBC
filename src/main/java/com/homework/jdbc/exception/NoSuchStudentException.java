package com.homework.jdbc.exception;

public class NoSuchStudentException extends Throwable {
    public NoSuchStudentException(String s) {
        System.err.println(s);
    }
}
