package com.develop.bookstore.domain.user.application.auth;

import com.develop.bookstore.domain.user.domain.auth.Password;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.dto.auth.PasswordDTO;
import com.develop.bookstore.global.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    /**
     * 회원 ID로 비밀번호 조회.
     */
    public Password getPasswordByMemberId(String memberNo) {
        List<Password> passwordList = passwordRepository.getPasswordByMemberId(memberNo);
        if (CollectionUtils.isEmpty(passwordList)) {
            throw new NoSuchEntityException("회원 정보가 없습니다.");
        }
        if (passwordList.size() > 1) {
            throw new RuntimeException("회원 비밀번호 정보가 다중으로 존재합니다.");
        }
        return passwordList.get(0);
    }


}
