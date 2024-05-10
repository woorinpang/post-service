package io.woorinpang.postservice.core.domain.post.application;

import io.woorinpang.postservice.core.domain.post.domain.AddCommentCommand;
import io.woorinpang.postservice.core.domain.post.domain.PostCommentAppender;
import io.woorinpang.postservice.core.domain.post.domain.PostTarget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentService {
    private final PostCommentAppender postCommentAppender;

    public long addPostComment(PostTarget target, AddCommentCommand command) {
        return postCommentAppender.append(target, command);
    }
}
