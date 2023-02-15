package com.develop.bookstore.member.login.emailauth.controller;

import com.develop.bookstore.member.login.emailauth.dto.EmailAuthDTO;
import com.develop.bookstore.member.login.emailauth.service.sendmail.MailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MailSendController {

    private final MailSendService mailSendService;

    @RequestMapping("/email/sendAuthMail")
    public void sendAuthMail(@RequestBody EmailAuthDTO emailAuthDTO){
        log.info("emailAuthDTO : {} ", emailAuthDTO);
        mailSendService.sendAuthMail(emailAuthDTO);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/email/auth")
    public void signUp(@ModelAttribute EmailAuthDTO emailAuthDTO) {

    }
}
