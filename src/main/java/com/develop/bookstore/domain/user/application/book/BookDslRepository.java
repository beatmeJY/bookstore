package com.develop.bookstore.domain.user.application.book;

import com.develop.bookstore.domain.user.dto.book.BookSearchDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDslRepository {

    public List<BookSearchDTO> getBookList(String bookName, String authorName);
}
