package com.develop.bookstore.domain.user.application.book;

import com.develop.bookstore.domain.user.domain.book.Author;
import com.develop.bookstore.domain.user.domain.book.Book;
import com.develop.bookstore.domain.user.dto.book.BookDTO;
import com.develop.bookstore.domain.user.dto.book.BookRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public void saveBook(BookRegisterDTO dto, Author author, Long memberNo) {
        bookRepository.save(dto.toBookEntity(memberNo, author));
    }

    public BookDTO getBookById(Long id) {
        try {
            Book book = bookRepository.getEntityByIdOrElseThrow(id);
            return book.toBookDto();
        } catch (Exception e) {
            return null;
        }
    }
}
