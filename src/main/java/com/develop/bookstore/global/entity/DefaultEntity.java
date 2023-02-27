package com.develop.bookstore.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@MappedSuperclass
@Getter @Setter
public class DefaultEntity {

    @Id
    private Long id;

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

    @PrePersist
    public void generatedId() {
        StringBuffer id = new StringBuffer();
        id.append(Long.toString(System.currentTimeMillis()));
        id.append(RandomStringUtils.random(6));
        this.id = Long.getLong(id.toString());
    }

}
