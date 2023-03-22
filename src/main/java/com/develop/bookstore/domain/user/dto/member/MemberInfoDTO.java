package com.develop.bookstore.domain.user.dto.member;

import com.develop.bookstore.domain.user.domain.member.EGender;
import com.develop.bookstore.domain.user.domain.member.ELoginPlatform;

public record MemberInfoDTO (Long memberInfoId, String memberName, Integer birthYear, Integer birthMonth, Integer birthDay, EGender eGender, String contact, String contactSignYn,
                                String email, String emailSignYn, ELoginPlatform eLoginPlatform) {}
