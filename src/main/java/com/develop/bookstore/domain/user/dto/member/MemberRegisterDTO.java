package com.develop.bookstore.domain.user.dto.member;

import com.develop.bookstore.domain.user.domain.member.EGender;
import com.develop.bookstore.domain.user.domain.member.ELoginPlatform;
import com.develop.bookstore.domain.user.exception.UserInsertFailedException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;

public record MemberRegisterDTO(String memberId, String memberName, String nickName, Integer birth, EGender eGender, String contact, String email, ELoginPlatform eLoginPlatform,
        String address, String addressDetail, String postcode, String homeAddressYn, String password) {

    public MemberRegisterDTO {
        this.validation(memberId, "memberId");
        this.validation(memberName, "memberName");
        this.validation(nickName, "nickName");
        this.validation(birth, "birth");
        this.validation(eGender, "eGender");
        this.validation(contact, "contact");
        this.validation(email, "email");
        this.validation(eLoginPlatform, "eLoginPlatform");
        this.validation(address, "address");
        this.validation(addressDetail, "addressDetail");
        this.validation(postcode, "postcode");
        this.validation(homeAddressYn, "homeAddressYn");
        this.validation(password, "password");
    }

    public void validation(Object o, String name) {
        if (ObjectUtils.isEmpty(o)) {
            throw new UserInsertFailedException(name + "정보가 비어있습니다.");
        }
    }
}
