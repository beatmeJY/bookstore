package com.develop.bookstore.domain.member.application.auth;

import com.develop.bookstore.domain.member.api.MemberToMailClient;
import com.develop.bookstore.domain.member.dto.EmailAuthDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailAuthService {

    private final MemberToMailClient memberToMailClient;

    // 메일 전송
    public void sendAuthMail(EmailAuthDTO emailAuthDTO) {
        memberToMailClient.sendAuthMail(emailAuthDTO);
    }

    // 메일 인증키 적합 여부 판단.
    public Boolean compareEmailAuthKey(EmailAuthDTO dto) {
        //TODO 인증키 저장 테이블과 비교하여 인증 성공여부 리턴하기.
        return true;
    }


}
