package com.develop.bookstore.domain.user.application.auth;

import com.develop.bookstore.domain.user.domain.auth.Password;
import com.develop.bookstore.domain.user.dto.auth.PasswordDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordDslRepository {

    public List<Password> getPasswordByMemberId(String memberId);
}
