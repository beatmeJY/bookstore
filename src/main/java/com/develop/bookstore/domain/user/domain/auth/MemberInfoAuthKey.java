package com.develop.bookstore.domain.user.domain.auth;

import com.develop.bookstore.domain.user.domain.member.MemberInfo;
import com.develop.bookstore.global.entity.DefaultEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auth_member_info_auth_key")
@AttributeOverride(name = "id", column = @Column(name = "auth_key_id"))
@Getter @Setter
@NoArgsConstructor
public class MemberInfoAuthKey extends DefaultEntity {

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
    @JoinColumn(name = "member_info_id")
    private MemberInfo memberInfo;

    // 생성자
    public MemberInfoAuthKey(String authKey, String emailYn, String contactYn, String authCompleteYn, MemberInfo memberInfo) {
        this.authKey = authKey;
        this.emailYn = emailYn;
        this.contactYn = contactYn;
        this.authCompleteYn = authCompleteYn;
        this.memberInfo = memberInfo;
    }

}
