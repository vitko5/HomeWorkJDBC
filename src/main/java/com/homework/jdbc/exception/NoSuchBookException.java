package com.homework.jdbc.exception;

public class NoSuchBookException extends Throwable {
    public NoSuchBookException(String s) {
        System.err.println(s);
    }
}
