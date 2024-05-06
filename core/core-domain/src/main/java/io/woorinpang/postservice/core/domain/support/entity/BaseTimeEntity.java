package io.woorinpang.postservice.core.domain.support.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false, nullable = false) @Comment("생성일자")
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false) @Comment("수정일자")
    protected LocalDateTime lastModifiedDate;
}
