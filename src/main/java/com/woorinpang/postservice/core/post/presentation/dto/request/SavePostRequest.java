package com.woorinpang.postservice.core.post.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavePostRequest {

    private String postTitle;
    private String postContent;

    public void validate() {
        //validate check
    }

    @Builder
    public SavePostRequest(String postTitle, String postContent) {
        this.postTitle = postTitle;
        this.postContent = postContent;
    }
}
