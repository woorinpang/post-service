package com.woorinpang.postservice.core.post.presentation.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavePostResponse {

    private Long savedPostId;

    public SavePostResponse(Long savedPostId) {
        this.savedPostId = savedPostId;
    }
}
