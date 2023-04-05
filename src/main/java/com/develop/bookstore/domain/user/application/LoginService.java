package com.develop.bookstore.domain.user.application;

import com.develop.bookstore.domain.user.application.auth.PasswordService;
import com.develop.bookstore.domain.user.application.member.MemberService;
import com.develop.bookstore.domain.user.domain.auth.Password;
import com.develop.bookstore.domain.user.dto.auth.LoginDTO;
import com.develop.bookstore.domain.user.enumconst.SessionConst;
import com.develop.bookstore.domain.user.exception.auth.LoginFailedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService memberService;
    private final PasswordService passwordService;
    private final PasswordEncoder bCryptPasswordEncoder;


    /**
     * 로그인.
     */
    @Transactional(rollbackFor = Exception.class)
    public void login(LoginDTO dto, HttpServletRequest request) {
        try {
            // 패스워드 정보 검사.
            Password password = passwordService.getPasswordByMemberId(dto.memberId());
            password.comparePassword(dto.password(), bCryptPasswordEncoder);

            // 세션 생성 및 저장.
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER, password.getMember());
        } catch (Exception e) {
            throw new LoginFailedException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }

    /**
     * 로그아웃. (세션 정보 삭제)
     */
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // false를 줘야 없을경우 새로우 세션 생성 안함.
        if (ObjectUtils.isNotEmpty(session)) {
            session.invalidate();
        }
    }
}
