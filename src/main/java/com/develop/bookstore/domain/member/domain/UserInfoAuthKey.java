package com.develop.bookstore.domain.member.domain;

import com.develop.bookstore.global.entity.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member_user_info_auth_key")
@Getter @Setter
@NoArgsConstructor
public class UserInfoAuthKey extends DefaultEntity {

    // 인증키
    @Id
    @Column(nullable = false)
    private String authKeyId;

//     //유저 정보 ID
//    @Column(nullable = false)
//    private String userInfoId;

    // 인증키
    @Column(nullable = false)
    private String authKey;

    // 이메일 인증
    @Column(nullable = false, length = 1)
    private String emailYn;

    // 연락처 인증
    @Column(nullable = false, length = 1)
    private String contactYn;

    // 인증성공여부
    @Column(nullable = false, length = 1)
    private String authCompleteYn;

    @ManyToOne
    @JoinColumn(name = "user_info_id", referencedColumnName = "user_info_id")
    private UserInfo userInfo;

}
