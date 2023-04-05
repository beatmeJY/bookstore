package com.develop.bookstore.domain.user.exception.auth;

public class LoginFailedException extends RuntimeException {

    public LoginFailedException(String message) {
        super(message);
    }
}
