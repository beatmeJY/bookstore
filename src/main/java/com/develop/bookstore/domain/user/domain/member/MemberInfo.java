package com.develop.bookstore.domain.user.domain.member;

import com.develop.bookstore.domain.user.domain.member.auth.MemberAuthentication;
import com.develop.bookstore.domain.user.exception.UserInsertFailedException;
import com.develop.bookstore.global.entity.DefaultEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@AttributeOverride(name = "id", column = @Column(name = "member_info_id"))
@Entity
@Table(name = "user_member_info")
@Getter @Setter
@NoArgsConstructor
@Slf4j
public class MemberInfo extends DefaultEntity {

    // 닉네임.
    @Column(nullable = false)
    private String nickName;

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

    // 성별
    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private EGender eGender;

    // 연락처
    @Column(nullable = false, length = 15)
    private String contact;

    // 이메일
    @Column(nullable = false, length = 100)
    private String email;

    // 가입 플랫폼
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ELoginPlatform eLoginPlatform;

    // 사용자 No.
    @OneToOne
    @JoinColumn(name = "user_no", nullable = false)
    private Member member;


    // 집 주소 ID
    @OneToOne
    @JoinColumn(name = "address_id", nullable = true)
    private MemberAddress homeAddress;

    @OneToOne(mappedBy = "memberInfo")
    private MemberAuthentication memberAuthentication;


    /**
     * 생성자
     */
    public MemberInfo(Member member, String nickName, Integer birth,
            EGender eGender, String contact, String email, ELoginPlatform eLoginPlatform, MemberAddress homeAddress) {
        this.member = member;
        this.nickName = nickName;
        this.eGender = eGender;
        this.email = email;
        this.eLoginPlatform = eLoginPlatform;
        this.homeAddress = homeAddress;
        setBirthDay(birth);
        setContact(contact);
    }

    /**
     * 생년월일 저장 로직.
     * 첫 회원 등록 이후 생일은 변경 불가.
     * @param birthDay
     */
    private void setBirthDay(Integer birthDay) {
        if (ObjectUtils.isEmpty(birthDay)) throw new UserInsertFailedException("생년월일이 존재 하지 않습니다.");

        String stringBirthDay = birthDay.toString();
        if (stringBirthDay.length() != 8) throw new UserInsertFailedException("생년월일이 정보가 올바르지 않습니다.");

        LocalDate birthDayLocal = LocalDate.now();
        birthDayLocal = birthDayLocal.withYear(Integer.parseInt(stringBirthDay.substring(0, 4)));
        birthDayLocal = birthDayLocal.withMonth(Integer.parseInt(stringBirthDay.substring(4, 6)));
        birthDayLocal = birthDayLocal.withDayOfMonth(Integer.parseInt(stringBirthDay.substring(6)));

        if (LocalDate.now().isBefore(birthDayLocal)) {
            log.error("아직 태어나지 않은 사람 입니다.");
            throw new UserInsertFailedException("아직 태어나지 않은 사람 입니다.");
        }

        this.birthYear = birthDayLocal.getYear();
        this.birthMonth = birthDayLocal.getMonthValue();
        this.birthDay = birthDayLocal.getDayOfMonth();
    }

    /**
     * 전화번호 저장 로직.
     * @param phoneNumber
     */
    public void setContact(String phoneNumber) {
        if (!StringUtils.hasText(phoneNumber)) throw new UserInsertFailedException("전화번호가 비어있습니다.");
        StringBuilder result = new StringBuilder();

        String[] phoneNumSplit = phoneNumber.split("-");
        if (phoneNumSplit.length != 3) throw new UserInsertFailedException("전화번호 양식이 올바르지 않습니다.");

        for (String s : phoneNumSplit) {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                log.error("전화번호 양식이 올바르지 않습니다.");
                throw new UserInsertFailedException("전화번호 양식이 올바르지 않습니다.");
            }
        }
        this.contact = phoneNumber;
    }

    /**
     * 나이 꺼내기.
     * @return
     */
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

}
