package com.develop.bookstore.domain.user.exception;

public class UserInsertFailedException extends RuntimeException {

    public UserInsertFailedException(String message) {
        super(message);
    }
}
