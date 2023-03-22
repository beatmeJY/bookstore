package com.develop.bookstore.domain.user.application.auth;

import com.develop.bookstore.domain.user.api.MemberToMailClient;
import com.develop.bookstore.domain.user.domain.auth.MemberInfoAuthKey;
import com.develop.bookstore.domain.user.domain.member.MemberInfo;
import com.develop.bookstore.domain.user.dto.auth.EmailAuthDTO;
import com.develop.bookstore.domain.user.dto.member.MemberInfoDTO;
import com.develop.bookstore.global.enumconst.YnFlag;
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
        String authKey = UUID.randomUUID().toString();

        MemberInfo memberInfoEntity = memberInfoRepository.getEntityByIdOrElseThrow(memberInfoDTO.memberInfoId());

        MemberInfoAuthKey memberInfoAuthKey = new MemberInfoAuthKey(authKey, YnFlag.Y, YnFlag.N, YnFlag.N, memberInfoEntity);

        // 인증 메일 전송 요청 (메일 페인)
        EmailAuthDTO emailAuthDTO = new EmailAuthDTO(memberInfoDTO.email(), authKey);
        memberToMailClient.sendMail(emailAuthDTO);
    }

    // 메일 인증키 적합 여부 판단.
    public Boolean compareEmailAuthKey(EmailAuthDTO dto) {
        //TODO 인증키 저장 테이블과 비교하여 인증 성공여부 리턴하기.
        return true;
    }


}
