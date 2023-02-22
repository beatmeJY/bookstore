package com.develop.bookstore.domain.member.api;

import com.develop.bookstore.domain.member.application.auth.EmailAuthService;
import com.develop.bookstore.domain.member.dto.EmailAuthDTO;
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
public class UserInfoAuthController {

    private final EmailAuthService emailAuthService;

    @RequestMapping("/sendAuthMail")
    public void sendAuthMail(@RequestBody EmailAuthDTO emailAuthDTO){
        log.info("emailAuthDTO : {} ", emailAuthDTO);
        emailAuthService.sendAuthMail(emailAuthDTO);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/keyAuth")
    public String signUp(@ModelAttribute EmailAuthDTO emailAuthDTO) {
        emailAuthService.compareEmailAuthKey(emailAuthDTO);
        return "ok";
    }

}
