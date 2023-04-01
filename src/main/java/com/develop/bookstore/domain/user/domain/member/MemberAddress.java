package com.develop.bookstore.domain.user.domain.member;

import com.develop.bookstore.global.entity.DefaultEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AttributeOverride(name = "id", column = @Column(name = "address_id"))
@Entity
@Table(name = "user_member_address")
@Getter @Setter
@NoArgsConstructor
public class MemberAddress extends DefaultEntity {

    // 주소
    @Column(nullable = false)
    private String address;

    // 상세주소
    @Column(nullable = false)
    private String addressDetail;

    // 우편 번호
    private String postcode;

    // 집주소 여부
    @Column(nullable = false, length = 1)
    private String homeAddressYn;


    // 회원
    @ManyToOne
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    @OneToOne(mappedBy = "homeAddress")
    private MemberInfo memberInfo;

    /**
     * 최초 생성자
     */
    public MemberAddress(String address, String addressDetail, String postcode, String homeAddressYn, Member member) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.postcode = postcode;
        this.homeAddressYn = homeAddressYn;
        this.member = member;
    }
}
