package com.develop.bookstore.domain.user.application.auth;

import com.develop.bookstore.domain.user.domain.auth.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {
}
