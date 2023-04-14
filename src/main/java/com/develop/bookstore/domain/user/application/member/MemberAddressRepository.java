package com.develop.bookstore.domain.user.application.member;

import com.develop.bookstore.domain.user.domain.member.MemberAddress;
import com.develop.bookstore.global.exception.NoSuchEntityException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {

    default MemberAddress getEntityByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NoSuchEntityException("MemberAddress"));
    }

}
