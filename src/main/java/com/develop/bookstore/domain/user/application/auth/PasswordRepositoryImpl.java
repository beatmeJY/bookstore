package com.develop.bookstore.domain.user.application.auth;

import com.develop.bookstore.domain.user.domain.auth.Password;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

public class PasswordRepositoryImpl implements PasswordDslRepository {

    private final JPAQueryFactory queryFactory;

    public PasswordRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Password getPasswordByMemberId(String memberId) {
        return null;
    }
}
