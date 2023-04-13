package com.develop.bookstore.global.exception;

public class NotNullPropertyException extends RuntimeException {

    public NotNullPropertyException(String name) {
        super(name+ " 값은 필수 값 입니다.");
    }
}
