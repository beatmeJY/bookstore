package com.develop.bookstore.domain.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member_user_address")
@Getter @Setter
@NoArgsConstructor
public class UserAddress {

    // 주소 ID
    @Id
    private String addressId;

    // 주소
    @Column(nullable = false)
    private String address;

    // 상세주소
    @Column(nullable = false)
    private String addressDetail;

    // 우편 번호
    private String postcode;

    // 집주소 여부
    @Column(nullable = false, length = 1)
    private String home_address_yn;


    // 유저 정보
    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private User user;

    @OneToOne(mappedBy = "homeAddress")
    private UserInfo userInfo;
}
