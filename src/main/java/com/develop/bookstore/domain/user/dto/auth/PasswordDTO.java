package com.develop.bookstore.domain.user.dto.auth;


import com.querydsl.core.annotations.QueryProjection;

public record PasswordDTO (Long id, String password) {

    @QueryProjection
    public PasswordDTO(Long id, String password) {
        this.id = id;
        this.password = password;
    }
}
