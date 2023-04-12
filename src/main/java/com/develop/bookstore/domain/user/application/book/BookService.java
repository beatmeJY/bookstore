package com.develop.bookstore.domain.user.application.book;

import com.develop.bookstore.domain.user.application.member.MemberRepository;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.exception.member.UserInsertFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

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

    public Member getMemberById(String memberId) {
        List<Member> memberList = memberRepository.getMemberByMemberId(memberId);
        if (CollectionUtils.isEmpty(memberList)) {
            throw new RuntimeException("존재하지 않는 아이디 입니다.");
        }
        if (memberList.size() > 1) {
            throw new RuntimeException("1:1 관계에 다중 엔티티가 존재합니다.");
        }
        return memberList.get(0);
    }

}
