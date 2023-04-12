package com.develop.bookstore.domain.user.api.book;

import com.develop.bookstore.domain.user.application.MemberRegisterService;
import com.develop.bookstore.domain.user.dto.member.MemberRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final MemberRegisterService memberRegisterService;

    // 신규 회원가입
    @PostMapping("/addBook")
    public void registerMember(@RequestBody MemberRegisterDTO dto) {
        memberRegisterService.memberRegister(dto);
    }

    


}
