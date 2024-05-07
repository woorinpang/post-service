package io.woorinpang.postservice.core.domain.post.repository;

import io.woorinpang.postservice.core.domain.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static java.lang.Boolean.*;

@Entity
@Table(name = "Post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends BaseEntity {

    @Column(name = "userId", columnDefinition = "bigint not null comment '유저 고유번호'")
    private Long userId;

    @Column(name = "title", columnDefinition = "varchar(255) not null comment '제목'")
    private String title;

    @Column(name = "content", columnDefinition = "text null comment '내용'")
    private String content;

    @Column(name = "isDeleted", columnDefinition = "bit not null comment '삭제여부'")
    private boolean isDeleted;

    @Builder
    public PostEntity(long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.isDeleted = FALSE;
        this.createdBy = String.valueOf(userId);
        this.createdDate = LocalDateTime.now();
        this.lastModifiedBy = String.valueOf(userId);
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void delete() {
        this.isDeleted = TRUE;
    }
}
