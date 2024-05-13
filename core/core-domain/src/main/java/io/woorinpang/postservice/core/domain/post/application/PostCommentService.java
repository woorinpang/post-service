package io.woorinpang.postservice.core.domain.post.application;

import io.woorinpang.postservice.core.domain.post.domain.*;
import io.woorinpang.postservice.core.domain.post.repository.FindPagePostCommentProjection;
import io.woorinpang.postservice.core.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentService {
    private final PostCommentFinder postCommentFinder;
    private final PostCommentAppender postCommentAppender;
    private final PostCommentRemover postCommentRemover;

    public Page<FindPagePostCommentProjection> findPagePostComment(PostTarget target, Pageable pageable) {
        return postCommentFinder.findPagePostComment(target, pageable);
    }

    public long addPostComment(PostTarget target, AddCommentCommand command, User user) {
        return postCommentAppender.append(target, command, user);
    }

    public void deletePostComment(PostCommentTarget postCommentTarget) {
        postCommentRemover.remove(postCommentTarget);
    }
}
