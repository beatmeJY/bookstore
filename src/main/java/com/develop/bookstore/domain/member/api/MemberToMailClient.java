package com.develop.bookstore.domain.member.api;

import com.develop.bookstore.domain.member.dto.EmailAuthDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${interface.mail.url}", name="testClient")
public interface MemberToMailClient {

    @PostMapping(value = "/sendAuthMail", consumes = { MediaType.APPLICATION_JSON_VALUE })
    void sendAuthMail(EmailAuthDTO emailAuthDTO);

}
