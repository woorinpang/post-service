package io.woorinpang.postservice.core.api.controller.post.response;

import io.woorinpang.postservice.core.domain.post.repository.FindPagePostCommentProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPagePostCommentResponse {
    private String author;

    private String content;

    private LocalDateTime createdDate;

    public FindPagePostCommentResponse(FindPagePostCommentProjection comment) {
        this.author = comment.getAuthor();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();
    }
}
