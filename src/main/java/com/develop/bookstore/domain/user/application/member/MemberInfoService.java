package com.develop.bookstore.domain.user.application.member;

import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.domain.member.MemberAddress;
import com.develop.bookstore.domain.user.domain.member.MemberInfo;
import com.develop.bookstore.domain.user.dto.member.MemberRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberInfoRepository memberInfoRepository;


    // 신규 회원가입시 회원정보 저장.
    public void registerMemberInfo(Member member, MemberAddress memberAddress, MemberRegisterDTO dto) {
        MemberInfo memberInfo = new MemberInfo(member, dto.nickName(), dto.birth(), dto.eGender(), dto.contact(), dto.email(), dto.eLoginPlatform(), memberAddress);
        memberInfoRepository.save(memberInfo);
    }
}

