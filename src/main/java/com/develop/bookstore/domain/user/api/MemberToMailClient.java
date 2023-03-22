package com.develop.bookstore.domain.user.api;

import com.develop.bookstore.domain.user.dto.auth.EmailAuthDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${interface.mail.url}", name="testClient")
public interface MemberToMailClient {

    @PostMapping(value = "/sendMail", consumes = { MediaType.APPLICATION_JSON_VALUE })
    void sendMail(EmailAuthDTO emailAuthDTO);

}
