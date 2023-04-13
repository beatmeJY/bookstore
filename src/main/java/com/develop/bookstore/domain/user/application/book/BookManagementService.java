package com.develop.bookstore.domain.user.application.book;

import com.develop.bookstore.domain.user.domain.book.Author;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.dto.book.BookRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookManagementService {

    private final AuthorService authorService;
    private final BookService bookService;

    /**
     * 책 등록.
     */
    @Transactional(rollbackFor = Exception.class)
    public void addBook(BookRegisterDTO dto, Member member) {
        Author author = authorService.getEntityByIdOrElseThrow(dto.authorNo());
        bookService.saveBook(dto, author, member.getId());
    }
    
    
}
