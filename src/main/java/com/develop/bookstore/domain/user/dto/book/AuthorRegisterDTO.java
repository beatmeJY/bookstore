package com.develop.bookstore.domain.user.dto.book;

import com.develop.bookstore.domain.user.domain.book.Author;
import com.develop.bookstore.domain.user.exception.member.UserInsertFailedException;
import com.develop.bookstore.global.exception.NotNullPropertyException;
import org.apache.commons.lang3.ObjectUtils;

public record AuthorRegisterDTO(String authorName, Integer authorBirth, String authorInfo) {

    public AuthorRegisterDTO {
        this.validation(authorName, "authorName");
        this.validation(authorBirth, "authorBirth");
        this.validation(authorInfo, "authorInfo");
    }

    public void validation(Object o, String name) {
        if (ObjectUtils.isEmpty(o)) {
            throw new NotNullPropertyException(name + "정보가 비어있습니다.");
        }
    }

    /**
     * Author Entity 로 변환.
     */
    public Author toAuthorEntity(Long memberNo) {
        return new Author(authorName, authorBirth, authorInfo, memberNo);
    }
}
