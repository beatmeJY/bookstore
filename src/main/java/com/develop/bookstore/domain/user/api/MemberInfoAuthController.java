package com.develop.bookstore.domain.user.api;

import com.develop.bookstore.domain.user.application.auth.EmailAuthService;
import com.develop.bookstore.domain.user.dto.auth.EmailAuthDTO;
import com.develop.bookstore.domain.user.dto.member.MemberInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberInfoAuthController {

    private final EmailAuthService emailAuthService;

    @RequestMapping("/sendAuthMail")
    public void sendAuthMail(@RequestBody MemberInfoDTO memberInfoDTO){
        log.info("sendAuthMail memberInfoDTO : {} ", memberInfoDTO.toString());
        emailAuthService.sendAuthMail(memberInfoDTO);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/checkByKeyAuth")
    public String signUp(@ModelAttribute EmailAuthDTO emailAuthDTO) {
        emailAuthService.compareEmailAuthKey(emailAuthDTO);
        return "ok";
    }

}
