package com.develop.bookstore.domain.user.api.member;

import com.develop.bookstore.domain.user.application.MemberRegisterService;
import com.develop.bookstore.domain.user.dto.member.MemberRegisterDTO;
import com.develop.bookstore.domain.user.exception.member.UserInsertFailedException;
import com.develop.bookstore.global.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberRegisterService memberRegisterService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserInsertFailedException.class})
    public ResponseDTO userInsertFailedException(UserInsertFailedException e) {
        return new ResponseDTO(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    // 신규 회원가입
    @PostMapping("/memberRegister")
    public ResponseDTO registerMember(@RequestBody MemberRegisterDTO dto) {
        memberRegisterService.memberRegister(dto);
        return new ResponseDTO();
    }

}
