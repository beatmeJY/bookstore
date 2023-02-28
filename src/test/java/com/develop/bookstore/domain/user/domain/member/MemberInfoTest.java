package com.develop.bookstore.domain.user.domain.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.develop.bookstore.domain.user.exception.UserRegistFailedException;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberInfoTest {

    Member member1 = new Member("tester1", "테스터1");
    Member member2 = new Member("tester2", "테스터2");
    Member member3 = new Member("tester3", "테스터3");

    MemberInfo memberInfo1 = new MemberInfo(member1, "테스터1", 20001231, EGender.MAN,  "010-1111-1111", "N","test1@naver.com",
        "N", ELoginPlatform.NONE, null);
    MemberInfo memberInfo2 = new MemberInfo(member2, "테스터2", 20000101, EGender.WOMAN,  "010-2222-2222", "N","test2@naver.com",
        "N", ELoginPlatform.NONE, null);
    MemberInfo memberInfo3 = new MemberInfo(member3, "테스터3", 20000101, EGender.MAN,  "010-9999-9999", "N","test3@naver.com",
        "N", ELoginPlatform.NONE, null);


    @Test
    @DisplayName("전화번호 저장 테스트")
    void setContact() {
        memberInfo1.setContact("010-3333-3333");
        assertEquals(memberInfo1.getContact(),"010-3333-3333");

        // 잘못된 전화번호로 수정시 에러
        assertThrows(UserRegistFailedException.class, () -> memberInfo2.setContact("01010-98494-3412-314120"));
        assertEquals(memberInfo2.getContact(),"010-2222-2222");
        
        // 잘못된 전화번호 양식일때 에러(전화번호 구분자는 "-" 이여야 함.)
        assertThrows(UserRegistFailedException.class, () -> new MemberInfo(member3, "테스터3", 20000101, EGender.WOMAN, "01033333333", "N",
            "test3@naver.com", "N", ELoginPlatform.NONE, null));
    }

    @Test
    @DisplayName("생년월일 저장 테스트")
    void setBirthDay() {
        // 태어나지 않은 생년월일 에러
        assertThrows(UserRegistFailedException.class, () -> new MemberInfo(member1, "테스터1", 29990101, EGender.MAN,  "010-1111-1111", "N",
            "test1@naver.com", "N", ELoginPlatform.NONE, null));
    }

    @Test
    @DisplayName("현재 나이 구하는 로직 테스트")
    void getAge() {
        LocalDate now = LocalDate.now();

        // 만나이 적용일
        LocalDate onlyAgeStartDate = LocalDate.now();
        onlyAgeStartDate = onlyAgeStartDate.withYear(2023);
        onlyAgeStartDate = onlyAgeStartDate.withMonth(6);
        onlyAgeStartDate = onlyAgeStartDate.withDayOfMonth(1);

        // 23년 6월 이후부터는 만나이로 계산함.
        if (now.isAfter(onlyAgeStartDate)) {
            if (now.getMonthValue() == 12 && now.getDayOfMonth() == 31) {
                assertEquals(now.getYear() - 2000, memberInfo1.getAge()); // 만나이 지남.
                assertEquals(now.getYear() - 2000, memberInfo2.getAge()); // 만나이 지남.
            } else {
                assertEquals(now.getYear() - 2000, memberInfo1.getAge()); // 만나이 지남.
                assertEquals(now.getYear() - 2000 -1, memberInfo2.getAge()); // 만나이 안 지남.
            }
        } else {
            assertEquals(now.getYear() - 2000 + 1, memberInfo1.getAge());
            assertEquals(now.getYear() - 2000 + 1, memberInfo2.getAge());
        }
    }
}
