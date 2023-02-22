package com.develop.bookstore.domain.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ELoginPlatform {

    NAVER("네이버"),
    GOOGLE("구글"),
    FACEBOOK("페이스북"),
    KAKAO("카카오"),
    NONE("없음");

    private final String name;
}
