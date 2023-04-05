package com.develop.bookstore.domain.user.application.auth;

import com.develop.bookstore.domain.user.domain.auth.Password;
import com.develop.bookstore.domain.user.dto.auth.PasswordDTO;
import com.develop.bookstore.domain.user.dto.auth.QPasswordDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.develop.bookstore.domain.user.domain.auth.QPassword.password1;
import static com.develop.bookstore.domain.user.domain.member.QMember.member;

public class PasswordRepositoryImpl implements PasswordDslRepository {

    private final JPAQueryFactory queryFactory;

    public PasswordRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Password> getPasswordByMemberId(String memberId) {
        return (List<Password>) queryFactory
                .from(password1)
                .innerJoin(password1.member, member)
                .where(member.memberId.eq(memberId))
                .fetch();
    }
}
