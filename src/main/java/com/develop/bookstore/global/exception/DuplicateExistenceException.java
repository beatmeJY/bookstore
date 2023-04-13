package com.develop.bookstore.global.exception;

public class DuplicateExistenceException extends RuntimeException {

    public DuplicateExistenceException() {
        super("중복된 정보가 이미 존재합니다.");
    }

}
