package io.woorinpang.postservice.core.api.controller.post.request;

import io.woorinpang.postservice.core.domain.post.domain.AddCommentCommand;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AddCommentRequest {
    @Length(min = 1, max = 8, message = "작성자는 1~8글자로 입력해주세요.")
    @NotBlank(message = "작성자를 입력해주세요.")
    private String author;

    @Length(min = 6, max = 30, message = "비밀번호는 6~30글자로 입력해주세요.")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Length(min = 10, max = 1000, message = "내용은 10~1000자까지 입력해주세요.")
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    public AddCommentCommand toCommand() {
        return new AddCommentCommand(author, password, content);
    }
}
