package com.develop.bookstore.domain.user.application.member;

import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.domain.member.MemberAddress;
import com.develop.bookstore.domain.user.exception.UserInsertFailedException;
import com.develop.bookstore.global.enumconst.YnFlag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberAddressService {

    private final MemberAddressRepository memberAddressRepository;

    // 회원 정보 저장시 첫 저장.
    @Transactional(rollbackFor = Exception.class)
    public MemberAddress saveAddressByRegisterMember(Member member, String address, String addressDetail, String postCode) {
        MemberAddress memberAddress = new MemberAddress(address, addressDetail, postCode, YnFlag.Y, member);
        return memberAddressRepository.save(memberAddress);
    }

}
