package com.develop.bookstore.domain.user.api.book;

import com.develop.bookstore.domain.user.application.book.BookService;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.dto.book.BookRegisterDTO;
import com.develop.bookstore.domain.user.dto.member.MemberRegisterDTO;
import com.develop.bookstore.domain.user.enumconst.SessionConst;
import com.develop.bookstore.global.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    // 책 판매 등록.
    @PostMapping("/saveBook")
    public ResponseDTO addBook(@RequestBody BookRegisterDTO dto, HttpServletRequest request) {
        bookService.addBook(dto, (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER));
        return new ResponseDTO();
    }

    // 책 판매 수정.
    @PutMapping("/saveBook")
    public ResponseDTO modifyBook(@RequestBody MemberRegisterDTO dto) {
//        bookService.modifyBook(dto);
        return new ResponseDTO();
    }

    // 책 판매 삭제
    @DeleteMapping("/saveBook")
    public ResponseDTO deleteBook(@RequestBody MemberRegisterDTO dto) {
//        bookService.deleteBook(dto);
        return new ResponseDTO();
    }

}
