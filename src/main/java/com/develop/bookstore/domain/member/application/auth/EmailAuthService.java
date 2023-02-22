package com.develop.bookstore.domain.member.application.auth;

import com.develop.bookstore.domain.member.api.MemberToMailClient;
import com.develop.bookstore.domain.member.model.dto.EmailAuthDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailAuthService {

    private final MemberToMailClient memberFeignClient;

    // 메일 전송
    public void sendAuthMail(EmailAuthDTO emailAuthDTO) {
        memberFeignClient.sendAuthMail(emailAuthDTO);
    }

    // 메일 인증키 적합 여부 판단.
    public Boolean compareEmailAuthKey(EmailAuthDTO dto) {
        return true;
    }


}
