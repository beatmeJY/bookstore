package com.develop.bookstore.domain.user.dto.book;

import com.develop.bookstore.domain.user.domain.member.EGender;
import com.develop.bookstore.domain.user.domain.member.ELoginPlatform;
import com.develop.bookstore.domain.user.exception.member.UserInsertFailedException;
import org.apache.commons.lang3.ObjectUtils;

public record BookRegisterDTO(String bookName, String bookThumbnail, String detailImg, String trailer, Long authorNo, String translator, String publisher,
                              String publicationDay, Integer originPrice, Integer price, Integer earnPoints, Integer deliveryFee, String bookGenre,
                              String bookIntro, String index, String bookPreview, String publisherReview, Integer totalPage, Integer bookQty) {

    public BookRegisterDTO {
        this.validation(bookName, "bookName");
        this.validation(bookThumbnail, "bookThumbnail");
        this.validation(detailImg, "detailImg");
        this.validation(trailer, "trailer");
        this.validation(authorNo, "authorNo");
        this.validation(translator, "translator");
        this.validation(publisher, "publisher");
        this.validation(publicationDay, "publicationDay");
        this.validation(originPrice, "originPrice");
        this.validation(price, "price");
        this.validation(earnPoints, "earnPoints");
        this.validation(deliveryFee, "deliveryFee");
        this.validation(bookGenre, "bookGenre");
        this.validation(bookIntro, "bookIntro");
        this.validation(index, "index");
        this.validation(bookPreview, "bookPreview");
        this.validation(publisherReview, "publisherReview");
        this.validation(totalPage, "totalPage");
        this.validation(bookQty, "bookQty");
    }

    public void validation(Object o, String name) {
        if (ObjectUtils.isEmpty(o)) {
            throw new UserInsertFailedException(name + "정보가 비어있습니다.");
        }
    }
}
