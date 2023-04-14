package com.develop.bookstore.domain.user.dto.book;

import com.develop.bookstore.domain.user.domain.book.Author;
import com.develop.bookstore.domain.user.domain.book.Book;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record BookSearchDTO(Long bookNo, String bookName, String bookThumbnail, String detailImg, String trailer, String translator, String publisher,
                            Integer publicationDay, Integer originPrice, Integer price, Integer earnPoints, Integer deliveryFee, String bookGenre,
                            String bookIntro, String index, String bookPreview, String publisherReview, Integer totalPage, Integer bookQty, Integer likeNum,
                            String authorName, LocalDateTime regDt) {

    @QueryProjection
    public BookSearchDTO {
    }

    public Double getSalePercent() {
        return (double) this.price() / (double) this.originPrice();
    }

    public Book toBookEntity(Long memberNo, Author author) {
        return new Book(bookName, bookThumbnail, detailImg, trailer, translator, publisher, publicationDay,
                price, originPrice, earnPoints, deliveryFee, bookGenre, bookIntro, index, bookPreview,
                publisherReview, totalPage, bookQty, likeNum,  memberNo, author);
    }
}
