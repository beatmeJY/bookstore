package com.develop.bookstore.domain.user.domain;

import com.develop.bookstore.global.entity.DefaultEntity;
import com.develop.bookstore.global.exception.NotFormatMatchException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "user_member_info")
@Getter @Setter
@NoArgsConstructor
public class MemberInfo extends DefaultEntity {

    // 유저 정보 ID.
    @Id
    private String memberInfoId;

    // 사용자 이름
    @Column(nullable = false)
    private String memberName;

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
    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private EGender eGender;

    // 연락처
    @Column(nullable = false, length = 15)
    private String contact;

    // 이메일 인증여부
    @Column(nullable = false, length = 1)
    private String contactSignYn;

    // 이메일
    @Column(nullable = false, length = 100)
    private String email;

    // 이메일 인증여부
    @Column(nullable = false, length = 1)
    private String emailSignYn;

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

    @OneToMany(mappedBy = "memberInfo")
    private List<MemberInfoAuthKey> memberInfoAuthKey;


    /**
     * 생성자
     */
    public MemberInfo(String memberInfoId, Member member, MemberAddress homeAddress, String memberName, Integer birthYear, Integer birthMonth, Integer birthDay, EGender eGender, String contact, String contactSignYn, String email, String emailSignYn,
        ELoginPlatform eLoginPlatform) {

        this.memberInfoId = memberInfoId;
        this.member = member;
        this.homeAddress = homeAddress;
        this.memberName = memberName;
        this.eGender = eGender;
        this.contactSignYn = contactSignYn;
        this.email = email;
        this.emailSignYn = emailSignYn;
        this.eLoginPlatform = eLoginPlatform;
        setBirthDay(birthDay);
        setPhoneNumber(contact);
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
        if (!StringUtils.hasText(phoneNumber)) throw new NotFormatMatchException("전화번호가 비어있습니다.");
        StringBuilder result = new StringBuilder();

        String[] phoneNumSplit = phoneNumber.split("-");
        if (phoneNumSplit.length > 3) throw new NotFormatMatchException("전화번호 양식이 올바르지 않습니다.");

        for (String s : phoneNumSplit) {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new NotFormatMatchException("전화번호 양식이 올바르지 않습니다.");
            }
        }
        this.contact = phoneNumber;
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

}
