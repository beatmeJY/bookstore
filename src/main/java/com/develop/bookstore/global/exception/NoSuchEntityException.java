package com.develop.bookstore.global.exception;

public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException(Object o) {
        super(o.getClass().getName() + "엔티티 정보가 없습니다.");
    }
}
