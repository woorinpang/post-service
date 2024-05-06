package io.woorinpang.postservice.core.domain.post;

import io.woorinpang.postservice.core.domain.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Column(name = "title", columnDefinition = "varchar(255) not null comment '제목'")
    private String title;

    @Column(name = "content", columnDefinition = "text null comment '내용'")
    private String content;

    @Column(name = "userId", columnDefinition = "bigint not null comment '유저 고유번호'")
    private Long userId;

    @Builder
    public Post(long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdBy = String.valueOf(userId);
        this.createdDate = LocalDateTime.now();
        this.lastModifiedBy = String.valueOf(userId);
        this.lastModifiedDate = LocalDateTime.now();
    }
}
