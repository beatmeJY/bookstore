package com.develop.bookstore.domain.user.application;

import com.develop.bookstore.domain.user.application.auth.PasswordService;
import com.develop.bookstore.domain.user.application.member.MemberAddressService;
import com.develop.bookstore.domain.user.application.member.MemberInfoService;
import com.develop.bookstore.domain.user.application.member.MemberService;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.domain.member.MemberAddress;
import com.develop.bookstore.domain.user.dto.member.MemberRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberRegisterService {

    private final MemberService memberService;
    private final MemberAddressService memberAddressService;
    private final MemberInfoService memberinfoService;
    private final PasswordService passwordService;

    /**
     * 회원 신규 저장 (회원, 회원 정보)
     * 회원 정보 저장도 실패시 모두 롤백.
     */
    @Transactional(rollbackFor = Exception.class)
    public void memberRegister(MemberRegisterDTO dto) {
        // 회원 신규 저장.
        Member member = memberService.registerMember(dto.memberId(), dto.memberName());

        // 회원 주소 저장.
        MemberAddress memberAddress = memberAddressService.saveAddressByRegisterMember(member, dto.address(), dto.addressDetail(), dto.postcode());

        // 회원 정보 저장.
        memberinfoService.registerMemberInfo(member, memberAddress, dto);

        // 패스워드 암호화 해서 저장.
        passwordService.savePasswordByRegisterMember(member, dto.password());
    }

}
