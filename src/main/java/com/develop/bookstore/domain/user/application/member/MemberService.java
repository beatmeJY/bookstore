package com.develop.bookstore.domain.user.application.member;

import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.exception.UserInsertFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 첫 회원 가입.
     */
    @Transactional(rollbackFor = Exception.class)
    public Member registerMember(String memberId, String memberName) {
        List<Member> memberList = memberRepository.getMemberByMemberId(memberId);
        if (!CollectionUtils.isEmpty(memberList)) {
            throw new UserInsertFailedException("해당 ID는 이미 존재하는 ID 입니다.");
        }

        Member member = new Member(memberId, memberName);
        return memberRepository.save(member);
    }

}
