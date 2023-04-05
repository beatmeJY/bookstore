package com.develop.bookstore.domain.user.exception.member;

public class UserInsertFailedException extends RuntimeException {

    public UserInsertFailedException(String message) {
        super(message);
    }
}
