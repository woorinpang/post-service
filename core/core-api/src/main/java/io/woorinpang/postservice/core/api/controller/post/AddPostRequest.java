package io.woorinpang.postservice.core.api.controller.post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddPostRequest {
    private String title;
}
