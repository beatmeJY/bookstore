package com.develop.bookstore.domain.user.dto.book;

import com.develop.bookstore.domain.user.domain.book.Author;
import com.develop.bookstore.domain.user.domain.book.Book;
import com.develop.bookstore.domain.user.exception.member.UserInsertFailedException;
import com.querydsl.core.annotations.QueryProjection;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;

public record BookMainSetDTO(Long bookNo) {


    public BookMainSetDTO {
        this.validation(bookNo, "bookNo");
    }

    public void validation(Object o, String name) {
        if (ObjectUtils.isEmpty(o)) {
            throw new UserInsertFailedException(name + "정보가 비어있습니다.");
        }
    }
}
