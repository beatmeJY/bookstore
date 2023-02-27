package com.develop.bookstore.domain.user.exception;

public class UserRegistFailedException extends RuntimeException {

    public UserRegistFailedException(String message) {
        super(message);
    }
}
