package com.develop.bookstore.defalut.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public class DefaultEntity {

    // 생성자
    @Column
    private String regId;
    // 생성일시
    @Column
    private LocalDateTime regDt;

    // 수정자
    @Column
    private String modId;
    // 수정일시
    @Column
    private LocalDateTime modDt;

}
