package com.develop.bookstore.domain.user.dto.auth;

import com.develop.bookstore.domain.user.exception.UserInsertFailedException;
import org.apache.commons.lang3.ObjectUtils;

public record LoginDTO(String memberId, String password) {

    public LoginDTO {
        this.validation(memberId, "memberId");
        this.validation(password, "password");
    }

    public void validation(Object o, String name) {
        if (ObjectUtils.isEmpty(o)) {
            throw new UserInsertFailedException(name + "정보가 비어있습니다.");
        }
    }
}
