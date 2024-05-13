package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.PostCommentEntity;

public record AddCommentCommand(
        String author,
        String password,
        String content
) {
    public PostCommentEntity toPostCommentEntity(long postId, long userId) {
        return PostCommentEntity
                .builder()
                .postId(postId)
                .author(author)
                .password(password)
                .content(content)
                .createdBy(userId)
                .build();
    }
}
