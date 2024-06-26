package io.woorinpang.postservice.core.api.controller.post.request;

import io.woorinpang.postservice.core.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModifyPostRequest {
    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    private long userId;

    public User toUser() {
        return new User(userId);
    }
}
