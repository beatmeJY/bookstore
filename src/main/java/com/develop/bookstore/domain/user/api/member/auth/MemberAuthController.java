package com.develop.bookstore.domain.user.api.member.auth;

import com.develop.bookstore.domain.user.application.member.auth.EmailAuthService;
import com.develop.bookstore.domain.user.dto.auth.EmailAuthDTO;
import com.develop.bookstore.domain.user.dto.member.MemberInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/auth")
@Slf4j
public class MemberAuthController {

    private final EmailAuthService emailAuthService;

    @PostMapping("/sendAuthMail")
    public void sendAuthMail(@RequestBody MemberInfoDTO memberInfoDTO){
        log.info("sendAuthMail memberInfoDTO : {} ", memberInfoDTO.toString());
        emailAuthService.sendAuthMail(memberInfoDTO);
    }

    @GetMapping(path = "/checkByKeyAuth")
    public String signUp(@ModelAttribute EmailAuthDTO emailAuthDTO) {
        emailAuthService.compareEmailAuthKey(emailAuthDTO);
        return "ok";
    }

}
