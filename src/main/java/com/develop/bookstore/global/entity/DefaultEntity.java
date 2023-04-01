package com.develop.bookstore.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.method.annotation.SessionAttributesHandler;

@MappedSuperclass
@Getter @Setter
public class DefaultEntity {

    @Id
    private Long id;

    // 생성일시
    @Column
    private LocalDateTime regDt;

    // 수정일시
    @Column
    private LocalDateTime modDt;

    @PrePersist
    public void generatedId() {
        StringBuffer generatorId = new StringBuffer();
        generatorId.append(Long.toString(System.currentTimeMillis()));
        generatorId.append(RandomStringUtils.randomNumeric(6));
        this.id = Long.parseLong(generatorId.toString());
        this.regDt = LocalDateTime.now();
    }
}
