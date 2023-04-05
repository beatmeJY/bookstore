package com.develop.bookstore.domain.user.domain.auth;

import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.exception.auth.LoginFailedException;
import com.develop.bookstore.domain.user.exception.member.UserInsertFailedException;
import com.develop.bookstore.global.entity.DefaultEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

@AttributeOverride(name = "id", column = @Column(name = "password_id"))
@Entity
@Table(name = "auth_password")
@Getter @Setter
@NoArgsConstructor
@Slf4j
public class Password extends DefaultEntity {


    private String password;

    // 회원
    @OneToOne
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    // 최초 생성자
    public Password(Member member, String password, PasswordEncoder bCryptPasswordEncoder) {
        // 비밀번호 패턴 체크
        passwordPatternCheck(password);

        this.member = member;
        this.password = bCryptPasswordEncoder.encode(password);
}

    /**
     * 비밀번호 패턴 체크. (비밀번호는 영문과 특수문자, 숫자를 포함하며 8자 이상 20자 이하 이어야 함.)
     */
    private void passwordPatternCheck(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,20}$");
        if (!pattern.matcher(password).find()) {
            throw new UserInsertFailedException("비밀번호는 영문과 특수문자, 숫자를 포함하며 8자 이상 20자 이하 이어야 합니다.");
        }
    }

    // 비밀번호 비교.
    public void comparePassword(String password, PasswordEncoder bCryptPasswordEncoder) {
        if (!bCryptPasswordEncoder.matches(password, this.password)) {
            log.info("비밀번호 불일치");
            throw new LoginFailedException("비밀번호가 일치하지 않습니다!");
        }
    }
}
