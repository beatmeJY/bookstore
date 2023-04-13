package com.develop.bookstore.domain.user.domain.book;

import com.develop.bookstore.domain.user.domain.auth.Password;
import com.develop.bookstore.domain.user.domain.member.MemberAddress;
import com.develop.bookstore.domain.user.domain.member.MemberInfo;
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
    private String BookThumbnail;
    // 상세 이미지
    private String detailImg;
    // 트레일러
    private String trailer;
    // 번역가
    private String translator;
    // 출판사
    private String publisher;
    // 출판일
    private String publicationDay;
    // 가격
    private Integer price;
    // 원 가격
    private Integer originPrice;
    // 적립금
    private Integer earnPoints;
    // 배송비
    private Integer deliveryFee;
    // 책 장르
    private String bookGenre;
    // 책 인트로
    private String bookIntro;
    // 목차
    private String index;
    // 책 리뷰
    private String bookPreview;
    // 출판사 서평
    private String publisherReview;
    // 총 페이지 수
    private Integer totalPage;
    // 판매 개수
    private Integer bookQty;
    // 좋아요 수
    private Integer likeNum;
    // 삭제여부
    private String delYn;
    // 등록자
    private Long memberNo;

    @OneToOne
    @JoinColumn(name = "author_no", nullable = false)
    private Author author;


    public Book(BookRegisterDTO dto, Long memberNo) {
        this.bookName = dto.bookName();
        this.BookThumbnail = dto.bookThumbnail();
        this.detailImg = dto.detailImg();
        this.trailer = dto.trailer();
        this.translator = dto.translator();
        this.publisher = dto.publisher();
        this.publicationDay = dto.publicationDay();
        this.price = dto.price();
        this.originPrice = dto.originPrice();
        this.earnPoints = dto.earnPoints();
        this.deliveryFee = dto.deliveryFee();
        this.bookGenre = dto.bookGenre();
        this.bookIntro = dto.bookIntro();
        this.index = dto.index();
        this.bookPreview = dto.bookPreview();
        this.publisherReview = dto.publisherReview();
        this.totalPage = dto.totalPage();
        this.bookQty = dto.bookQty();
        this.likeNum = 0;
        this.delYn = YnFlag.N;
        this.memberNo = memberNo;
    }
}
