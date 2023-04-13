package com.develop.bookstore.domain.user.api.auth;

import com.develop.bookstore.domain.user.application.LoginService;
import com.develop.bookstore.domain.user.dto.auth.LoginDTO;
import com.develop.bookstore.global.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ResponseDTO userInsertFailedException(Exception e) {
        return new ResponseDTO(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    // 로그인
    @PostMapping("/login")
    public ResponseDTO registerMember(@RequestBody LoginDTO dto, HttpServletRequest request) {
        loginService.login(dto, request);
        return new ResponseDTO();
    }

    @PostMapping("/logout")
    public ResponseDTO logout(HttpServletRequest request) {
        loginService.logout(request);
        return new ResponseDTO();
    }

}
