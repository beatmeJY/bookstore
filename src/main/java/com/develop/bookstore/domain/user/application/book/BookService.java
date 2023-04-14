package com.develop.bookstore.domain.user.application.book;

import com.develop.bookstore.domain.user.domain.book.Author;
import com.develop.bookstore.domain.user.domain.book.Book;
import com.develop.bookstore.domain.user.dto.book.BookMainSetDTO;
import com.develop.bookstore.domain.user.dto.book.BookModifyDTO;
import com.develop.bookstore.domain.user.dto.book.BookRegisterDTO;
import com.develop.bookstore.domain.user.dto.book.BookSearchDTO;
import com.develop.bookstore.global.enumconst.YnFlag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 최근 등록순으로 조회.
     */
    @Transactional(readOnly = true)
    public List<BookSearchDTO> getRecentBookList() {
        List<BookSearchDTO> result = bookRepository.getRecentBookList();
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.stream().limit(3).collect(Collectors.toList());
    }

    /**
     * 인기 순으로 정렬해서 조회.
     * @return
     */
    @Transactional(readOnly = true)
    public List<BookSearchDTO> getPopularBookList() {
        List<BookSearchDTO> result = bookRepository.getPopularBookList();
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.stream().limit(3).collect(Collectors.toList());
    }

    /**
     * 메인 책 정보 조회.
     */
    @Transactional(readOnly = true)
    public BookSearchDTO getMainBookInfo() {
        Book mainBook = bookRepository.findBookByMainYn(YnFlag.Y);
        if (ObjectUtils.isEmpty(mainBook)) {
            return null;
        }
        return mainBook.toBookDto();
    }

    @Transactional(rollbackFor = Exception.class)
    public void setMainBook(BookMainSetDTO dto) {
        Book beforeMainBook = bookRepository.findBookByMainYn(YnFlag.Y);
        beforeMainBook.setMainYn(YnFlag.N);
        Book newMainBook = bookRepository.getEntityByIdOrElseThrow(dto.bookNo());
        newMainBook.setMainYn(YnFlag.Y);
    }
}
