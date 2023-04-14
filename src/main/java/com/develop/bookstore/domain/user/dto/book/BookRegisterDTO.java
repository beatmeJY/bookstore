package com.develop.bookstore.domain.user.dto.book;

import com.develop.bookstore.domain.user.domain.book.Author;
import com.develop.bookstore.domain.user.domain.book.Book;
import com.develop.bookstore.domain.user.exception.member.UserInsertFailedException;
import org.apache.commons.lang3.ObjectUtils;

public record BookRegisterDTO(String bookName, String bookThumbnail, String detailImg, String trailer, Long authorNo, String translator, String publisher,
                              Integer publicationDay, Integer originPrice, Integer price, Integer earnPoints, Integer deliveryFee, String bookGenre,
                              String bookIntro, String index, String bookPreview, String publisherReview, Integer totalPage, Integer bookQty) {

    public BookRegisterDTO {
        this.validation(bookName, "bookName");
        this.validation(authorNo, "authorNo");
        this.validation(publisher, "publisher");
        this.validation(publicationDay, "publicationDay");
        this.validation(originPrice, "originPrice");
        this.validation(price, "price");
        this.validation(deliveryFee, "deliveryFee");
        this.validation(bookGenre, "bookGenre");
        this.validation(bookIntro, "bookIntro");
        this.validation(totalPage, "totalPage");
        this.validation(bookQty, "bookQty");
    }

    public void validation(Object o, String name) {
        if (ObjectUtils.isEmpty(o)) {
            throw new UserInsertFailedException(name + "정보가 비어있습니다.");
        }
    }

    public Book toBookEntity(Long memberNo, Author author) {
        return new Book(bookName, bookThumbnail, detailImg, trailer, translator, publisher, publicationDay,
                price, originPrice, earnPoints, deliveryFee, bookGenre, bookIntro, index, bookPreview,
                publisherReview, totalPage, bookQty, 0, memberNo, author);
    }
}
