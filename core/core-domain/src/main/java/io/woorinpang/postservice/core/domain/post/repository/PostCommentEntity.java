package io.woorinpang.postservice.core.domain.post.repository;

import io.woorinpang.postservice.core.domain.support.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCommentEntity extends BaseEntity {
    @Column(name = "postId", columnDefinition = "bigint not null comment '게시글 고유번호'")
    private long postId;

    @Column(name = "author", columnDefinition = "varchar(60) not null comment '작성자'")
    private String author;

    @Column(name = "password", columnDefinition = "varchar(120) not null comment '비밀번호'")
    private String password;

    @Column(name = "content", columnDefinition = "varchar(1000) not null comment '내용'")
    private String content;

    @Builder
    public PostCommentEntity(long postId, String author, String password, String content) {
        this.postId = postId;
        this.author = author;
        this.password = password;
        this.content = content;
    }
}
