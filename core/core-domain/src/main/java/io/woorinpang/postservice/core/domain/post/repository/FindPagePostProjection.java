package io.woorinpang.postservice.core.domain.post.repository;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPagePostProjection {
    private long postId;
    private String title;
    private long userId;
    private LocalDateTime createdDate;

    @QueryProjection
    public FindPagePostProjection(long postId, String title, long userId, LocalDateTime createdDate) {
        this.postId = postId;
        this.title = title;
        this.userId = userId;
        this.createdDate = createdDate;
    }
}
