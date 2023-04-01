package com.develop.bookstore.domain.user.application.member.auth;

import com.develop.bookstore.domain.user.api.MemberToMailClient;
import com.develop.bookstore.domain.user.application.member.MemberInfoRepository;
import com.develop.bookstore.domain.user.domain.member.MemberInfo;
import com.develop.bookstore.domain.user.dto.auth.EmailAuthDTO;
import com.develop.bookstore.domain.user.dto.member.MemberInfoDTO;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailAuthService {

    private final MemberToMailClient memberToMailClient;
    private final MemberInfoRepository memberInfoRepository;

    // 메일 전송
    @Transactional(rollbackFor = Exception.class)
    public void sendAuthMail(MemberInfoDTO memberInfoDTO) {
        LocalDateTime nowLocalDate = LocalDateTime.now();
        String authKey = UUID.randomUUID().toString();

        MemberInfo memberInfoEntity = memberInfoRepository.getEntityByIdOrElseThrow(memberInfoDTO.memberInfoId());

        //TODO 회원인증 테이블에서 조회해서 email 인증키 새로 발급하고 인증키 발급시간 갱신.

        // 인증 메일 전송 요청 (메일 feign)
        EmailAuthDTO emailAuthDTO = new EmailAuthDTO(memberInfoDTO.email(), authKey);
        memberToMailClient.sendMail(emailAuthDTO);
    }

    // 메일 인증키 적합 여부 판단.
    public Boolean compareEmailAuthKey(EmailAuthDTO dto) {
        //TODO 인증키 저장 테이블과 비교하여 인증 성공여부 리턴하기.
        return true;
    }


}
