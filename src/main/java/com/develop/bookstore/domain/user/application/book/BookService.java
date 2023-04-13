package com.develop.bookstore.domain.user.application.book;

import com.develop.bookstore.domain.user.domain.book.Book;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.dto.book.BookRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    @Transactional
    public void addBook(BookRegisterDTO dto, Member member) {
        Book book = new Book(dto, member.getId());
        member.getId();
        bookRepository.save(book);

    }
}
