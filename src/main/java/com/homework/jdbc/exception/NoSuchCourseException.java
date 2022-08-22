package com.homework.jdbc.exception;

public class NoSuchCourseException extends Throwable {
    public NoSuchCourseException(String s) {
        System.err.println(s);
    }
}
