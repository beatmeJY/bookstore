package com.develop.bookstore.member.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {

    @Id
    @Column
    private String userId;

    @Column
    private String userName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phoneNumber1;

    @Column
    private String phoneNumber2;

    @Column
    private String phoneNumber3;

    @Column
    private Integer birthYear;

    @Column
    private Integer birthMonth;

    @Column
    private Integer birthDay;


    public int getAge() {
        LocalDate localDate = LocalDate.now();
        //TODO - 23년 6월부터는 만나이로 계산하기.
        return localDate.getYear() - getBirthYear();
    }

}
