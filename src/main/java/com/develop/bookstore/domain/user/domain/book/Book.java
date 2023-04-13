package com.develop.bookstore.domain.user.domain.book;

import com.develop.bookstore.domain.user.domain.auth.Password;
import com.develop.bookstore.domain.user.domain.member.MemberAddress;
import com.develop.bookstore.domain.user.domain.member.MemberInfo;
import com.develop.bookstore.domain.user.dto.book.BookDTO;
import com.develop.bookstore.domain.user.dto.book.BookRegisterDTO;
import com.develop.bookstore.global.entity.DefaultEntity;
import com.develop.bookstore.global.enumconst.YnFlag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AttributeOverride(name = "id", column = @Column(name = "book_no"))
@Entity
@Table(name = "book_info")
@Getter @Setter
@NoArgsConstructor
public class Book extends DefaultEntity {

    // 책 제목.
    @Column(nullable = false)
    private String bookName;
    // 책 썸네일
    private String bookThumbnail;
    // 상세 이미지
    private String detailImg;
    // 트레일러
    private String trailer;
    // 번역가
    private String translator;
    // 출판사
    @Column(nullable = false)
    private String publisher;
    // 출판일
    @Column(nullable = false)
    private Integer publicationDay;
    // 가격
    @Column(nullable = false)
    private Integer price;
    // 원 가격
    @Column(nullable = false)
    private Integer originPrice;
    // 적립금
    private Integer earnPoints;
    // 배송비
    @Column(nullable = false)
    private Integer deliveryFee;
    // 책 장르
    @Column(nullable = false)
    private String bookGenre;
    // 책 인트로 강조
    @Column(length = 4000)
    private String bookIntroBold;
    // 책 인트로
    @Column(nullable = false, length = 4000)
    private String bookIntro;
    // 목차
    @Column(length = 4000)
    private String index;
    // 책 속으로
    @Column(length = 4000)
    private String bookPreview;
    // 출판사 서평
    @Column(length = 4000)
    private String publisherReview;
    // 총 페이지 수
    @Column(nullable = false)
    private Integer totalPage;
    // 판매 개수
    @Column(nullable = false)
    private Integer bookQty;
    // 좋아요 수
    @Column(nullable = false)
    private Integer likeNum;
    // 삭제여부
    @Column(nullable = false)
    private String delYn;
    // 등록자
    @Column(nullable = false)
    private Long memberNo;

    @OneToOne
    @JoinColumn(name = "author_no", nullable = false)
    private Author author;


    public Book(String bookName, String bookThumbnail, String detailImg, String trailer, String translator, String publisher, Integer publicationDay,
                Integer price, Integer originPrice, Integer earnPoints, Integer deliveryFee, String bookGenre, String bookIntro, String index, String bookPreview,
                String publisherReview, Integer totalPage, Integer bookQty, Long memberNo, Author author) {
        this.bookName = bookName;
        this.bookThumbnail = bookThumbnail;
        this.detailImg = detailImg;
        this.trailer = trailer;
        this.translator = translator;
        this.publisher = publisher;
        this.publicationDay = publicationDay;
        this.price = price;
        this.originPrice = originPrice;
        this.earnPoints = earnPoints;
        this.deliveryFee = deliveryFee;
        this.bookGenre = bookGenre;
        this.bookIntro = bookIntro;
        this.index = index;
        this.bookPreview = bookPreview;
        this.publisherReview = publisherReview;
        this.totalPage = totalPage;
        this.bookQty = bookQty;
        this.likeNum = 0;
        this.delYn = YnFlag.N;
        this.memberNo = memberNo;
        this.author = author;
    }

    public BookDTO toBookDto() {
        return new BookDTO(bookName, bookThumbnail, detailImg, trailer, author.getId(), translator, publisher, publicationDay, originPrice, price, earnPoints, deliveryFee, bookGenre,
                bookIntro, index, bookPreview, publisherReview, totalPage, bookQty);
    }
}
