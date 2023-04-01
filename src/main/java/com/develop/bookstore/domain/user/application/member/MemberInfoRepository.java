package com.develop.bookstore.domain.user.application.member;

import com.develop.bookstore.domain.user.domain.member.MemberInfo;
import com.develop.bookstore.global.exception.NoSuchEntityException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {

    default MemberInfo getEntityByIdOrElseThrow(Long aLong) {
        return findById(aLong).orElseThrow(() -> new NoSuchEntityException(MemberInfo.class));
    }



}
