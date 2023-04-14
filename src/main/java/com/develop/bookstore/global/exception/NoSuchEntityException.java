package com.develop.bookstore.global.exception;

public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException(String name) {
        super(name + " 정보가 없습니다.");
    }
}
