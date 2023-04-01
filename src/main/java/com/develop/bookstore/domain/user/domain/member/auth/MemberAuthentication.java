package com.develop.bookstore.domain.user.domain.member.auth;

import com.develop.bookstore.domain.user.domain.member.MemberInfo;
import com.develop.bookstore.global.entity.DefaultEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_authentication")
@AttributeOverride(name = "id", column = @Column(name = "auth_id"))
@Getter @Setter
@NoArgsConstructor
public class MemberAuthentication extends DefaultEntity {

    // 이메일 인증키
    @Column
    private String emailAuthKey;

    // 이메일 인증키 발급일시
    @Column
    private LocalDateTime emailAuthKeyDt;

    // 이메일 인증 완료 여부
    @Column(nullable = false, length = 1)
    private String emailCompleteYn;

    // 연락처 인증키
    @Column
    private String contactAuthKey;

    // 연락처 인증키 발급일시
    @Column
    private LocalDateTime contactAuthKeyDt;

    // 연락처 인증 완료 여부
    @Column(nullable = false, length = 1)
    private String contactCompleteYn;

    @OneToOne
    @JoinColumn(name = "member_info_id")
    private MemberInfo memberInfo;

    // 생성자
    public MemberAuthentication(String emailAuthKey, LocalDateTime emailAuthKeyDt, String emailCompleteYn, String contactAuthKey, LocalDateTime contactAuthKeyDt, String contactCompleteYn, MemberInfo memberInfo) {
        this.emailAuthKey = emailAuthKey;
        this.emailAuthKeyDt = emailAuthKeyDt;
        this.emailCompleteYn = emailCompleteYn;
        this.contactAuthKey = contactAuthKey;
        this.contactAuthKeyDt = contactAuthKeyDt;
        this.contactCompleteYn = contactCompleteYn;
        this.memberInfo = memberInfo;
    }

}
