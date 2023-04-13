package com.develop.bookstore.domain.user.api.book;

import com.develop.bookstore.domain.user.application.book.BookManagementService;
import com.develop.bookstore.domain.user.application.book.BookService;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.dto.book.BookRegisterDTO;
import com.develop.bookstore.domain.user.dto.member.MemberRegisterDTO;
import com.develop.bookstore.domain.user.enumconst.SessionConst;
import com.develop.bookstore.global.dto.ResponseDTO;
import com.develop.bookstore.global.dto.ResponseDataDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookManagementService bookManagementService;
    private final BookService bookService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ResponseDTO userInsertFailedException(Exception e) {
        return new ResponseDTO(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    // 책 판매 등록.
    @PostMapping("/saveBook")
    public ResponseDTO addBook(@RequestBody BookRegisterDTO dto, HttpServletRequest request) {
        bookManagementService.addBook(dto, (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER));
        return new ResponseDTO();
    }

    // 책 판매 수정.
    @PutMapping("/saveBook")
    public ResponseDTO modifyBook(@RequestBody MemberRegisterDTO dto) {
//        bookManagementService.modifyBook(dto);
        return new ResponseDTO();
    }

    // 책 판매 삭제
    @DeleteMapping("/saveBook")
    public ResponseDTO deleteBook(@RequestBody MemberRegisterDTO dto) {
//        bookManagementService.deleteBook(dto);
        return new ResponseDTO();
    }

    // 책 조회
    @GetMapping("/getBook")
    public ResponseDataDTO deleteBook(@RequestParam("bookNo") Long bookNo) {
        ResponseDataDTO result = new ResponseDataDTO();
        result.setData(bookService.getBookById(bookNo));
        return result;
    }

}
