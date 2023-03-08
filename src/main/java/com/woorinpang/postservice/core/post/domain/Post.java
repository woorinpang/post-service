package com.woorinpang.postservice.core.post.domain;

import com.woorinpang.servlet.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "post_title", nullable = false)
    private String title;
    @Lob
    @Column(name = "post_content")
    private String content;

    private int hits;

    @Builder(builderMethodName = "createPost")
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.hits = 0;
    }
}
