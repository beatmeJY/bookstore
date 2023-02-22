package com.develop.bookstore.domain.member.domain;

import com.develop.bookstore.global.entity.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member_user")
@Getter @Setter
@NoArgsConstructor
public class User extends DefaultEntity {

    // 사용자 No.
    @Id
    private String userNo;

    // 사용자 ID.
    @Column(nullable = false)
    private String userId;

    // 닉네임.
    @Column(nullable = false)
    private String nickName;


    @OneToOne(mappedBy = "user")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user")
    private List<UserAddress> userAddress;
}
