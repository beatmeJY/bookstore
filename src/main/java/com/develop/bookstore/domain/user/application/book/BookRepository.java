package com.develop.bookstore.domain.user.application.book;

import com.develop.bookstore.domain.user.domain.book.Book;
import com.develop.bookstore.domain.user.dto.book.BookSearchDTO;
import com.develop.bookstore.global.exception.NoSuchEntityException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookDslRepository{

    default Book getEntityByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NoSuchEntityException("Book"));
    }

    Book findBookByMainYn(String mainYn);
}
