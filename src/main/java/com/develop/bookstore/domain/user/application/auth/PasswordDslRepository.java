package com.develop.bookstore.domain.user.application.auth;

import com.develop.bookstore.domain.user.domain.auth.Password;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordDslRepository {

    public Password getPasswordByMemberId(String memberId);
}
