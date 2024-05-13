package io.woorinpang.postservice.core.domain.post.repository;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPagePostCommentProjection {
    private String author;

    private String content;

    private LocalDateTime createdDate;

    @QueryProjection
    public FindPagePostCommentProjection(String author, String content, LocalDateTime createdDate) {
        this.author = author;
        this.content = content;
        this.createdDate = createdDate;
    }
}
