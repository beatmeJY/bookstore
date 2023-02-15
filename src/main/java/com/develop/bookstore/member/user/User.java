package com.develop.bookstore.member.user;

import com.develop.bookstore.member.user.staticdata.ELoginPlatform;
import com.develop.bookstore.defalut.entity.DefaultEntity;
import com.develop.bookstore.defalut.exception.NotFormatMatchException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "member_user")
@Getter @Setter
@NoArgsConstructor
public class User extends DefaultEntity {

    // 사용자 ID
    @Id
    @Column
    private String userId;

    // 사용자 이름
    @Column(nullable = false)
    private String userName;

    // 사용자 이름
    @Column(nullable = false)
    private String nickName;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 생년
    @Column(nullable = false)
    @Getter(AccessLevel.PRIVATE)
    private Integer birthYear;

    // 생월
    @Column(nullable = false)
    @Getter(AccessLevel.PRIVATE)
    private Integer birthMonth;

    // 생일
    @Column(nullable = false)
    @Getter(AccessLevel.PRIVATE)
    private Integer birthDay;

    // 이메일
    @Column
    private String email;

    // 성별
    @Column
    private String gender;

    // 주소
    @Column
    private String address;

    // 상세주소
    @Column
    private String addressDetail;

    // 전화번호 처음자리
    @Column
    @Getter(AccessLevel.PRIVATE)
    private String phoneNumber1;

    // 전화번호 중간자리
    @Column
    @Getter(AccessLevel.PRIVATE)

    private String phoneNumber2;

    // 전화번호 끝자리
    @Column
    @Getter(AccessLevel.PRIVATE)
    private String phoneNumber3;

    // 가입 플랫폼
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ELoginPlatform eLoginPlatform;

    /**
     * 전체 생성자
     */
    public User(String userId, String userName, String nickName, String email, String password, String gender, Integer birthDay, String phoneNumber,
            String address, String addressDetail, ELoginPlatform eLoginPlatform) {
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.addressDetail = addressDetail;
        this.gender = gender;
        this.eLoginPlatform = eLoginPlatform;
        setBirthDay(birthDay);
        setPhoneNumber(phoneNumber);
    }

    /**
     * 필수 값 생성자
     */
    public User(String userId, String userName, String nickName, String password, Integer birthDay, ELoginPlatform eLoginPlatform) {
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.eLoginPlatform = eLoginPlatform;
        setBirthDay(birthDay);
    }

    // 이후 생일은 변경 불가.
    private void setBirthDay(Integer birthDay) {
        if (ObjectUtils.isEmpty(birthDay)) throw new NotFormatMatchException("생년월일이 존재 하지 않습니다.");

        String stringBirthDay = birthDay.toString();
        if (stringBirthDay.length() != 8) throw new NotFormatMatchException("생년월일이 정보가 올바르지 않습니다.");

        LocalDate birthDayLocal = LocalDate.now();
        birthDayLocal = birthDayLocal.withYear(Integer.parseInt(stringBirthDay.substring(0, 4)));
        birthDayLocal = birthDayLocal.withMonth(Integer.parseInt(stringBirthDay.substring(4, 6)));
        birthDayLocal = birthDayLocal.withDayOfMonth(Integer.parseInt(stringBirthDay.substring(6)));

        if (LocalDate.now().isBefore(birthDayLocal)) {
            throw new NotFormatMatchException("아직 태어나지 않은 사람 입니다.");
        }

        this.birthYear = birthDayLocal.getYear();
        this.birthMonth = birthDayLocal.getMonthValue();
        this.birthDay = birthDayLocal.getDayOfMonth();
    }

    public void setPhoneNumber(String phoneNumber) {
        if (StringUtils.hasText(phoneNumber)) {
            String[] phoneNumSplit = phoneNumber.split("-");
            for (String s : phoneNumSplit) {
                try {
                    Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    throw new NotFormatMatchException("전화번호 양식이 올바르지 않습니다.");
                }
            }

            if (phoneNumSplit.length != 3) {
                throw new NotFormatMatchException("전화번호 양식이 올바르지 않습니다.");
            }
            this.phoneNumber1 = phoneNumSplit[0];
            this.phoneNumber2 = phoneNumSplit[1];
            this.phoneNumber3 = phoneNumSplit[2];
        }
    }

    public int getAge() {
        LocalDate localDate = LocalDate.now();
        int result = localDate.getYear() - getBirthYear();

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
        if (!ObjectUtils.isEmpty(phoneNumber1) && !ObjectUtils.isEmpty(phoneNumber2) && !ObjectUtils.isEmpty(phoneNumber3)) {
            return phoneNumber1 + "-" + phoneNumber2 + "-" + phoneNumber3;
        }
        return null;
    }
}
