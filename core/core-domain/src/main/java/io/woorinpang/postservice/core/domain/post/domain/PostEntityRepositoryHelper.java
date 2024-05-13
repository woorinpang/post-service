package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.PostEntity;
import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;

public final class PostEntityRepositoryHelper {
    public static PostEntity findPostEntityById(final PostEntityRepository repository, final long postId) {
        return repository
                .findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }
}
