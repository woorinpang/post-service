package io.woorinpang.postservice.core.api.controller.post.response;

import io.woorinpang.postservice.core.domain.post.repository.FindPagePostProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPagePostResponse {
    private long postId;

    private String title;

    private long userId;

    private LocalDateTime createdDate;

    public FindPagePostResponse(FindPagePostProjection post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.userId = post.getUserId();
        this.createdDate = post.getCreatedDate();
    }
}
