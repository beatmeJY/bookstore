package com.develop.bookstore.domain.user.application.member;

import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.domain.member.MemberAddress;
import com.develop.bookstore.global.exception.NoSuchEntityException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {

    default MemberAddress getEntityByIdOrElseThrow(Long aLong) {
        return findById(aLong).orElseThrow(() -> new NoSuchEntityException(MemberAddress.class));
    }

}
