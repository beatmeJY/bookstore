package com.develop.bookstore.domain.user.api.book;

import com.develop.bookstore.domain.user.application.book.AuthorService;
import com.develop.bookstore.domain.user.application.book.BookService;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.dto.book.AuthorRegisterDTO;
import com.develop.bookstore.domain.user.dto.book.BookRegisterDTO;
import com.develop.bookstore.domain.user.dto.member.MemberRegisterDTO;
import com.develop.bookstore.domain.user.enumconst.SessionConst;
import com.develop.bookstore.global.dto.ResponseDTO;
import com.develop.bookstore.global.dto.ResponseDataDTO;
import com.develop.bookstore.global.dto.ResponseDataListDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @ExceptionHandler
    public ResponseDTO exceptionHandler(Exception e) {
        return new ResponseDTO(400, e.getMessage());
    }

    // 저자등록
    @PostMapping("/saveAuthor")
    public ResponseDTO addAuthor(@RequestBody AuthorRegisterDTO dto, HttpServletRequest request) {
        authorService.addAuthor(dto, (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER));
        return new ResponseDTO();
    }

    // 저자 수정
    // TODO - 추후 개발
    @PutMapping("/saveBook")
    public ResponseDTO modifyAuthor(@RequestBody MemberRegisterDTO dto) {
//        authorService.modifyBook(dto);
        return new ResponseDTO();
    }

    // 저자 삭제
    // TODO - 추후 개발
    @DeleteMapping("/saveBook")
    public ResponseDTO deleteAuthor(@RequestBody MemberRegisterDTO dto) {
//        authorService.deleteBook(dto);
        return new ResponseDTO();
    }

    // 저자 검색
    @GetMapping("/getAuthor")
    public ResponseDataListDTO<?> getAuthor(@RequestParam("authorName") String authorName) {
        ResponseDataListDTO result = new ResponseDataListDTO();
        result.setData(authorService.getAuthor(authorName));
        return result;
    }

}
