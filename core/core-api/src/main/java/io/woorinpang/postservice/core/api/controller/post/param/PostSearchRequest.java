package io.woorinpang.postservice.core.api.controller.post.param;

import io.woorinpang.postservice.core.domain.post.repository.PostSearchCondition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostSearchRequest {
    private String title;

    private String content;

    public PostSearchCondition toPostSearchCondition() {
        return new PostSearchCondition(title, content);
    }
}
