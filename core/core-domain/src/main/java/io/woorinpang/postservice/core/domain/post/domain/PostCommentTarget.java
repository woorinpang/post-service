package io.woorinpang.postservice.core.domain.post.domain;

public record PostCommentTarget(
        long postId,
        long commentId
) {
}
