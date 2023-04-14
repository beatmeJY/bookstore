package com.develop.bookstore.domain.user.application.member;

import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.global.exception.NoSuchEntityException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member getEntityByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NoSuchEntityException("Member"));
    }

    public List<Member> getMemberByMemberId(String memberId);


}
