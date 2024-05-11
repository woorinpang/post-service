package io.woorinpang.postservice.core.domain.post.application;

import io.woorinpang.postservice.core.domain.post.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentService {
    private final PostCommentAppender postCommentAppender;
    private final PostCommentRemover postCommentRemover;

    public long addPostComment(PostTarget target, AddCommentCommand command) {
        return postCommentAppender.append(target, command);
    }

    public void deletePostComment(PostCommentTarget postCommentTarget) {
        postCommentRemover.remove(postCommentTarget);
    }
}
