package com.develop.bookstore.domain.user.dto.book;

import com.develop.bookstore.global.exception.NotNullPropertyException;
import org.apache.commons.lang3.ObjectUtils;

public record AuthorSearchDTO(Long authorNo, String authorName, Integer authorBirth) {

    public AuthorSearchDTO {
        this.validation(authorNo, "authorNo");
        this.validation(authorName, "authorName");
        this.validation(authorBirth, "authorBirth");
    }

    public void validation(Object o, String name) {
        if (ObjectUtils.isEmpty(o)) {
            throw new NotNullPropertyException(name + "정보가 비어있습니다.");
        }
    }
}
