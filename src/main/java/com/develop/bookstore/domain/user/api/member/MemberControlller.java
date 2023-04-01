package com.develop.bookstore.domain.user.api.member;

import com.develop.bookstore.domain.user.application.MemberRegisterService;
import com.develop.bookstore.domain.user.application.member.MemberService;
import com.develop.bookstore.domain.user.dto.member.MemberRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberControlller {

    private final MemberRegisterService memberRegisterService;

    // 신규 회원가입
    @PostMapping("/memberRegister")
    public void registerMember(@RequestBody MemberRegisterDTO dto) {
        memberRegisterService.memberRegister(dto);
    }



}
