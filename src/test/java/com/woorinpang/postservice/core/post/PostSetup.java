package com.woorinpang.postservice.core.post;

import com.woorinpang.postservice.core.post.application.dto.command.SavePostCommand;
import com.woorinpang.postservice.core.post.domain.Post;

public class PostSetup {

    public static final String TITLE = "Java 17의 주요 기능";
    public static final String CONTENT = "Java 17은 많은 기능이 추가되었다.";

    public static Post setPost() {
        return Post.createPost()
                .title(TITLE)
                .content(CONTENT)
                .build();
    }

    public static SavePostCommand setSavePostCommand() {
        return SavePostCommand.builder()
                .title(TITLE)
                .content(CONTENT)
                .build();
    }
}
