package com.develop.bookstore.domain.user.application.book;

import com.develop.bookstore.domain.user.domain.book.Author;
import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.dto.book.AuthorRegisterDTO;
import com.develop.bookstore.domain.user.dto.book.AuthorSearchDTO;
import com.develop.bookstore.global.exception.DuplicateExistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;


    /**
     * 저자 등록
     */
    @Transactional(rollbackFor = Exception.class)
    public void addAuthor(AuthorRegisterDTO dto, Member member) {
        Author author = dto.toAuthorEntity(member.getId());

        // 중복 등록 체크
        List<Author> authorList = authorRepository.getAuthorByAuthorName(author.getAuthorName());
        if (authorList.stream().anyMatch(f -> f.getAuthorBirth().equals(author.getAuthorBirth()))) {
            throw new DuplicateExistenceException();
        }

        authorRepository.save(author);
    }

    public List<AuthorSearchDTO> getAuthor(String authorName) {
        List<Author> authorList = authorRepository.getAuthorByAuthorName(authorName);
        if (CollectionUtils.isEmpty(authorList)) {
            return null;
        }
        return authorList.stream().map(Author::toAuthorSearchDto).collect(Collectors.toList());
    }
}
