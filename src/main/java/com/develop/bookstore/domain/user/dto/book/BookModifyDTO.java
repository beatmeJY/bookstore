package com.develop.bookstore.domain.user.dto.book;

import com.develop.bookstore.domain.user.exception.member.UserInsertFailedException;
import org.apache.commons.lang3.ObjectUtils;

public record BookModifyDTO(Long bookNo, String bookName, String bookThumbnail, String detailImg, String trailer, String translator, String publisher,
                            Integer publicationDay, Integer originPrice, Integer price, Integer earnPoints, Integer deliveryFee, String bookGenre,
                            String bookIntro, String index, String bookPreview, String publisherReview, Integer totalPage, Integer bookQty) {

    public BookModifyDTO {
        this.validation(bookNo, "bookNo");
    }

    public void validation(Object o, String name) {
        if (ObjectUtils.isEmpty(o)) {
            throw new UserInsertFailedException(name + "정보가 비어있습니다.");
        }
    }

}
