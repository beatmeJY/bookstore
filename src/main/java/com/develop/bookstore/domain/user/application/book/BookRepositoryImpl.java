package com.develop.bookstore.domain.user.application.book;

import com.develop.bookstore.domain.user.dto.book.BookSearchDTO;
import com.develop.bookstore.domain.user.dto.book.QBookSearchDTO;
import com.develop.bookstore.global.enumconst.YnFlag;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.develop.bookstore.domain.user.domain.book.QBook.book;

public class BookRepositoryImpl implements BookDslRepository {

    private final JPAQueryFactory queryFactory;

    public BookRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<BookSearchDTO> getBookList(String bookName, String authorName) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotEmpty(bookName)) {
            builder.and(book.bookName.contains(bookName));
        }
        if (StringUtils.isNotEmpty(authorName)) {
            builder.and(book.author.authorName.contains(authorName));
        }
        return queryFactory
                .select(new QBookSearchDTO(
                        book.id,
                        book.bookName,
                        book.bookThumbnail,
                        book.detailImg,
                        book.trailer,
                        book.translator,
                        book.publisher,
                        book.publicationDay,
                        book.originPrice,
                        book.price,
                        book.earnPoints,
                        book.deliveryFee,
                        book.bookGenre,
                        book.bookIntro,
                        book.index,
                        book.bookPreview,
                        book.publisherReview,
                        book.totalPage,
                        book.bookQty,
                        book.likeNum,
                        book.delYn,
                        book.author.authorName
                ))
                .from(book)
                .where(
                        book.delYn.eq(YnFlag.N),
                        builder
                )
                .fetch();
    }
}
