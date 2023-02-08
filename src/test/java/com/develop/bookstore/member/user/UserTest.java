package com.develop.bookstore.member.user;

import static org.junit.jupiter.api.Assertions.*;

import com.develop.bookstore.member.user.staticdata.ELoginPlatform;
import com.develop.defalut.exception.NotFormatMatchException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void setPhoneNumber() {
        User user1 = new User("TEST1", "테스트1", "test1@naver.com", "test1", "남", 20000101,
            "010-1234-5678","", "", ELoginPlatform.NONE);
        User user2 = new User("TEST2", "테스트2", "test2", 20001231, ELoginPlatform.NONE);
        User user3 = new User("TEST3", "테스트3", "test3@naver.com", "test3", "남", 20000101,
            "010-9999-9999","", "",  ELoginPlatform.NONE);
        user1.setPhoneNumber("010-1111-1111");
        user2.setPhoneNumber("010-2222-2222");

        assertThrows(NotFormatMatchException.class, () -> user3.setPhoneNumber("01010984943412314120"));
        assertEquals(user1.getPhoneNumber(),"010-1111-1111");
        assertEquals(user2.getPhoneNumber(),"010-2222-2222");
        assertEquals(user3.getPhoneNumber(),"010-9999-9999");
    }

    @Test
    void getAge() {
        User user1 = new User("TEST1", "테스트1", "test1", 20000101, ELoginPlatform.NONE);
        User user2 = new User("TEST2", "테스트2", "test2", 20001231, ELoginPlatform.NONE);

        LocalDate now = LocalDate.now();
        LocalDate onlyAgeDate = LocalDate.now(); // 만나이 적용일
        onlyAgeDate = onlyAgeDate.withYear(2023);
        onlyAgeDate = onlyAgeDate.withMonth(6);
        onlyAgeDate = onlyAgeDate.withDayOfMonth(1);

        assertThrows(NotFormatMatchException.class, () -> new User("UnbornUser", "태어나지않음", "unbornUser", 20240101, ELoginPlatform.NONE));
        if (now.isAfter(onlyAgeDate)) {
            if (now.getMonthValue() == 12 && now.getDayOfMonth() == 31) {
                assertEquals(now.getYear() - 2000, user1.getAge()); // 만나이 지남.
                assertEquals(now.getYear() - 2000, user2.getAge()); // 만나이 지남.
            } else {
                assertEquals(now.getYear() - 2000, user1.getAge()); // 만나이 지남.
                assertEquals(now.getYear() - 2000 -1, user2.getAge()); // 만나이 안 지남.
            }
        } else {
            assertEquals(now.getYear() - 2000 + 1, user1.getAge());
            assertEquals(now.getYear() - 2000 + 1, user2.getAge());
        }
    }

    @Test
    void getPhoneNumber() {
        User user1 = new User("TEST1", "테스트1", "test1@naver.com", "test1", "남", 20000101,
            "010-1234-5678","", "", ELoginPlatform.NONE);
        User user2 = new User("TEST2", "테스트2", "test2@naver.com", "test2", "여", 20001231,
            "", "", "", ELoginPlatform.NONE);

        assertThrows(NotFormatMatchException.class, () ->
            new User("TEST3", "테스트3", "test3@naver.com", "test3", "여", 20001231,
                "010-9876-543@","", "", ELoginPlatform.NONE));
        assertEquals(user1.getPhoneNumber(), "010-1234-5678");
        assertEquals(user2.getPhoneNumber(), null);
    }

}
