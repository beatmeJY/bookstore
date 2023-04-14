package com.develop.bookstore.domain.user.application.book;

import com.develop.bookstore.domain.user.domain.book.Author;
import com.develop.bookstore.domain.user.domain.book.Book;
import com.develop.bookstore.domain.user.dto.book.BookModifyDTO;
import com.develop.bookstore.domain.user.dto.book.BookRegisterDTO;
import com.develop.bookstore.domain.user.dto.book.BookSearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public void saveBook(BookRegisterDTO dto, Author author, Long memberNo) {
        bookRepository.save(dto.toBookEntity(memberNo, author));
    }

    /**
     * 책 상세 조회
     */
    public BookSearchDTO getBookById(Long id) {
        try {
            Book book = bookRepository.getEntityByIdOrElseThrow(id);
            return book.toBookDto();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 책 목록 검색.
     */
    public List<BookSearchDTO> getBookList(String bookName, String authorName) {
        return bookRepository.getBookList(bookName, authorName);
    }

    /**
     * 책 수정.
     */
    @Transactional(rollbackFor = Exception.class)
    public void modifyBook(BookModifyDTO dto) {
        Book bookEntity = bookRepository.getEntityByIdOrElseThrow(dto.bookNo());
        bookEntity.patch(dto);
    }

    /**
     * 책 삭제.
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteBook(Long bookNo) {
        // TODO 추후 리뷰 개발 시 책 리뷰 있으면 삭제 불가능 추가하기.
        Book bookEntity = bookRepository.getEntityByIdOrElseThrow(bookNo);
        bookRepository.delete(bookEntity);
    }
}
