package io.woorinpang.postservice.core.domain.support.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends BaseTimeEntity {
    @CreatedBy
    @Column(updatable = false, nullable = false, length = 60) @Comment("생성자")
    protected String createdBy;

    @LastModifiedBy
    @Column(nullable = false, length = 60) @Comment("수정자")
    protected String lastModifiedBy;

    protected void createUserId(long userId) {
        this.createdBy = String.valueOf(userId);
        this.lastModifiedBy = String.valueOf(userId);
    }

    protected void updateUserId(long userId) {
        this.lastModifiedBy = String.valueOf(userId);
    }
}
