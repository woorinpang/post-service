package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.PostCommentEntity;
import io.woorinpang.postservice.core.domain.post.repository.PostCommentEntityRepository;

public final class PostCommentEntityHelper {
    public static PostCommentEntity findPostCommentById(final PostCommentEntityRepository repository, final long commentId) {
        return repository
                .findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }
}
