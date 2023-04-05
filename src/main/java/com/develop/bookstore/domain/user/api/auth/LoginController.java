package com.develop.bookstore.domain.user.api.auth;

import com.develop.bookstore.domain.user.application.LoginService;
import com.develop.bookstore.domain.user.dto.auth.LoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public void registerMember(@RequestBody LoginDTO dto, HttpServletRequest request) {
        loginService.login(dto, request);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        loginService.logout(request);
    }

}
