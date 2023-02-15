package com.develop.bookstore.defalut.exception;

public class NotFormatMatchException extends RuntimeException {

    private String message;
    public NotFormatMatchException(String message) {
        this.message = message;
    }
}
