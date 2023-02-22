package com.develop.bookstore.domain.mail.api;

import com.develop.bookstore.domain.mail.application.MailSendService;
import com.develop.bookstore.domain.member.dto.EmailAuthDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mail")
public class MailSendController {

    private final MailSendService mailSendService;


    @RequestMapping("/sendAuthMail")
    public void sendAuthMail(@RequestBody EmailAuthDTO emailAuthDTO){
        log.info("emailAuthDTO : {} ", emailAuthDTO);
        mailSendService.sendAuthMail(emailAuthDTO);
    }

}
