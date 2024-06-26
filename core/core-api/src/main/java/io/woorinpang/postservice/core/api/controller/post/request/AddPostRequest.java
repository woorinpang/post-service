package io.woorinpang.postservice.core.api.controller.post.request;

import io.woorinpang.postservice.core.domain.post.domain.PostContent;
import io.woorinpang.postservice.core.domain.user.User;
import io.woorinpang.postservice.core.domain.user.UserWithTargetId;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AddPostRequest {
    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    @NotBlank(message = "작성자를 입력하세요.")
    private String author;

    public PostContent toPostContent() {
        return new PostContent(title, content, author);
    }
}
