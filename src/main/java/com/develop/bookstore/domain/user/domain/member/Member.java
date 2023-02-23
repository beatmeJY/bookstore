package com.develop.bookstore.domain.user.domain.member;

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
@Table(name = "user_member")
@Getter @Setter
@NoArgsConstructor
public class Member extends DefaultEntity {

    // 사용자 No.
    @Id
    private String memberNo;

    // 사용자 ID.
    @Column(nullable = false)
    private String memberId;

    // 닉네임.
    @Column(nullable = false)
    private String nickName;


    @OneToOne(mappedBy = "member")
    private MemberInfo memberInfo;

    @OneToMany(mappedBy = "member")
    private List<MemberAddress> userAddress;
}
