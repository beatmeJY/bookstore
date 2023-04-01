package com.develop.bookstore.domain.user.application;

import com.develop.bookstore.domain.user.application.auth.PasswordService;
import com.develop.bookstore.domain.user.application.member.MemberService;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.dto.auth.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService memberService;
    private final PasswordService passwordService;

    /**
     * 로그인.
     */
    @Transactional(rollbackFor = Exception.class)
    public void login(LoginDTO dto) {
        try {
            passwordService.getPasswordByMemberNo(dto.memberId());
        } catch (Exception e) {
            throw new RuntimeException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }

}
