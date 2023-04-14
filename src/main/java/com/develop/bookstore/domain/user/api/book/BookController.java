package com.develop.bookstore.domain.user.api.book;

import com.develop.bookstore.domain.user.application.book.BookManagementService;
import com.develop.bookstore.domain.user.application.book.BookService;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.dto.book.BookModifyDTO;
import com.develop.bookstore.domain.user.dto.book.BookRegisterDTO;
import com.develop.bookstore.domain.user.enumconst.SessionConst;
import com.develop.bookstore.global.dto.ResponseDTO;
import com.develop.bookstore.global.dto.ResponseDataDTO;
import com.develop.bookstore.global.dto.ResponseDataListDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
    @PostMapping("/addBook")
    public ResponseDTO addBook(@RequestBody BookRegisterDTO dto, HttpServletRequest request) {
        bookManagementService.addBook(dto, (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER));
        return new ResponseDTO();
    }

    // 책 판매 수정.
    @PatchMapping("/modifyBook")
    public ResponseDTO modifyBook(@RequestBody BookModifyDTO dto) {
        bookService.modifyBook(dto);
        return new ResponseDTO();
    }

    // 책 판매 삭제
    @DeleteMapping("/deleteBook")
    public ResponseDTO deleteBook(@RequestParam("bookNo") Long bookNo) {
        bookService.deleteBook(bookNo);
        return new ResponseDTO();
    }

    // 책 검색
    @GetMapping("/getBookList")
    public ResponseDataListDTO getBookList(@RequestParam("bookName") String bookName, @RequestParam("authorName") String authorName) {
        ResponseDataListDTO result = new ResponseDataListDTO();
        result.setData(bookService.getBookList(bookName, authorName));
        return result;
    }


    // 책 상세 조회
    @GetMapping("/getBookDetail")
    public ResponseDataDTO getBookDetail(@RequestParam("bookNo") Long bookNo) {
        ResponseDataDTO result = new ResponseDataDTO();
        result.setData(bookService.getBookById(bookNo));
        return result;
    }

}
