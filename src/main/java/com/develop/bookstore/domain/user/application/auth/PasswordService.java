package com.develop.bookstore.domain.user.application.auth;

import com.develop.bookstore.domain.user.domain.auth.Password;
import com.develop.bookstore.domain.user.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final PasswordRepository passwordRepository;

    /**
     * 회원가입 시 최초 패스워드 저장.
     */
    public void savePasswordByRegisterMember(Member member, String password) {
        Password passwordEntity = new Password(member, password, bCryptPasswordEncoder);
        passwordRepository.save(passwordEntity);
    }

    public Password getPasswordByMemberNo(String memberNo) {
//        return passwordRepository
        return null;
    }


}
