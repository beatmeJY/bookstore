package com.develop.bookstore.domain.user.api.auth;

import com.develop.bookstore.domain.user.application.LoginService;
import com.develop.bookstore.domain.user.dto.auth.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    // 로그인
    @PostMapping("/login")
    public void registerMember(@RequestBody LoginDTO dto) {
        loginService.login(dto);
    }



}
