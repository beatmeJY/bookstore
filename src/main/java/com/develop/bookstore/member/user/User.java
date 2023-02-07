package com.develop.bookstore.member.user;

import com.develop.bookstore.member.user.staticdata.ELoginPlatform;
import com.develop.defalut.entity.DefaultEntity;
import com.develop.defalut.exception.NotFormatMatchException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "user")
@Getter @Setter
@NoArgsConstructor
public class User extends DefaultEntity {

    // 유저 ID
    @Id
    @Column
    private String userId;

    // 이름
    @Column(nullable = false)
    private String userName;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 생년
    @Column(nullable = false)
    private Integer birthYear;

    // 생월
    @Column(nullable = false)
    private Integer birthMonth;

    // 생일
    @Column(nullable = false)
    private Integer birthDay;

    // 이메일
    @Column
    private String email;

    // 성별
    @Column
    private String gender;

    // 전화번호 처음
    @Column
    private String phoneNumber1;

    // 전화번호 중간
    @Column
    private String phoneNumber2;

    // 전화번호 마지막
    @Column
    private String phoneNumber3;

    // 로그인 플랫폼
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ELoginPlatform eLoginPlatform;

    /**
     * 전체 생성자
     */
    public User(String userId, String userName, String email, String password, String gender, Integer birthDay, String phoneNumber, ELoginPlatform eLoginPlatform) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.eLoginPlatform = eLoginPlatform;
        setBirthDay(birthDay);
        setPhoneNumber(phoneNumber);
    }

    /**
     * 필수 값 생성자
     */
    public User(String userId, String userName, String password, Integer birthDay, ELoginPlatform eLoginPlatform) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.eLoginPlatform = eLoginPlatform;
        setBirthDay(birthDay);
    }


    public int getAge() {
        LocalDate localDate = LocalDate.now();
        int result = localDate.getYear() - getBirthYear();

        // TODO - 이거 테스트 코드 만들기.
        // 23년 6월부터는 만나이로 계산하기.
        if (localDate.getYear() >= 2023 && localDate.getMonthValue() >= 6) {
            if (this.getBirthMonth() > localDate.getMonthValue() || (this.getBirthMonth() == localDate.getMonthValue() && this.getBirthDay() > localDate.getDayOfMonth())) {
                return result - 1;
            }
            return result;
        }
        return result + 1;
    }

    public String getPhoneNumber() {
        if (ObjectUtils.isEmpty(phoneNumber1) || ObjectUtils.isEmpty(phoneNumber2) || ObjectUtils.isEmpty(phoneNumber3)) {
            return phoneNumber1 + "-" + phoneNumber2 + "-" + phoneNumber3;
        }
        return null;
    }

    public void setBirthDay(Integer birthDay) {
        if (ObjectUtils.isEmpty(birthDay)) throw new NotFormatMatchException("생년월일이 존재 하지 않습니다.");

        String stringBirthDay = birthDay.toString();
        if (stringBirthDay.length() != 8) throw new NotFormatMatchException("생년월일이 정보가 올바르지 않습니다.");

        this.birthYear = Integer.parseInt(stringBirthDay.substring(0, 4));
        this.birthMonth = Integer.parseInt(stringBirthDay.substring(4, 6));
        this.birthDay = Integer.parseInt(stringBirthDay.substring(6));
    }

    public void setPhoneNumber(String phoneNumber) {
        if (StringUtils.hasText(phoneNumber)) {
            String[] phoneNumSplit = phoneNumber.split("-");
            if (phoneNumSplit.length == 3) {
                this.phoneNumber1 = phoneNumSplit[0];
                this.phoneNumber2 = phoneNumSplit[1];
                this.phoneNumber3 = phoneNumSplit[2];
                throw new NotFormatMatchException("전화번호 양식이 올바르지 않습니다.");
            }
        }
    }

}
