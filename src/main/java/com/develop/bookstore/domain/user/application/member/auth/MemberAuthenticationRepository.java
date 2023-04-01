package com.develop.bookstore.domain.user.application.member.auth;

import com.develop.bookstore.domain.user.domain.member.auth.MemberAuthentication;
import com.develop.bookstore.global.exception.NoSuchEntityException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAuthenticationRepository extends JpaRepository<MemberAuthentication, Long> {

    default MemberAuthentication getEntityByIdOrElseThrow(Long aLong) {
        return findById(aLong).orElseThrow(() -> new NoSuchEntityException(MemberAuthentication.class));
    }



}
