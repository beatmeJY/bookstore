package com.develop.bookstore.domain.user.application.book;

import com.develop.bookstore.domain.user.domain.book.Author;
import com.develop.bookstore.domain.user.domain.book.Book;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.global.exception.NoSuchEntityException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    default Author getEntityByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NoSuchEntityException("Author"));
    }


    public List<Author> getAuthorByAuthorName(String AuthorName);

}
