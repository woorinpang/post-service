package io.woorinpang.postservice.core.domain.post.repository;

import io.woorinpang.postservice.core.domain.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.lang.Boolean.*;

@Entity
@Table(name = "Post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends BaseEntity {
    @Column(name = "title", columnDefinition = "varchar(255) not null comment '제목'")
    private String title;

    @Column(name = "content", columnDefinition = "text null comment '내용'")
    private String content;

    @Column(name = "author", columnDefinition = "varchar(60) not null comment '작성자'")
    private String author;

    @Column(name = "deleted", columnDefinition = "bit not null comment '삭제여부'")
    private boolean deleted;

    @Builder
    public PostEntity(String title, String content, String author, long userId) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.deleted = FALSE;
        this.createUserId(userId);
    }

    public void modify(String title, String content, long userId) {
        this.title = title;
        this.content = content;
        this.updateUserId(userId);
    }

    public void delete(long userId) {
        this.deleted = TRUE;
        this.updateUserId(userId);
    }
}
