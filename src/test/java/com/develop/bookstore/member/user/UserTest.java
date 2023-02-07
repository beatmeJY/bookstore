package com.develop.bookstore.member.user;

import static org.junit.jupiter.api.Assertions.*;

import com.develop.bookstore.member.user.staticdata.ELoginPlatform;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void getAge() {
        User user = new User("TEST1", "테스트1", "test1", 20000101, ELoginPlatform.NONE);
        assertEquals(LocalDate.now().getYear() - 2000 + 1, user.getAge());
    }

    @Test
    void getPhoneNumber() {
    }
}