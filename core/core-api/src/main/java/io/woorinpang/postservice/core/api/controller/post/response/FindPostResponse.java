package io.woorinpang.postservice.core.api.controller.post.response;

import io.woorinpang.postservice.core.domain.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPostResponse {
    private long postId;

    private String title;

    private String content;

    private String author;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    public FindPostResponse(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.createdDate = post.getCreatedDate();
        this.lastModifiedDate = post.getLastModifiedDate();
    }
}
