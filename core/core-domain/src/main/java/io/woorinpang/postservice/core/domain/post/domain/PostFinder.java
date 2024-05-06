package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostFinder {
    private final PostQueryRepository postQueryRepository;
    private final PostEntityRepository postEntityRepository;

    @Transactional(readOnly = true)
    public Page<FindPagePostProjection> findPagePost(PostSearchCondition condition, Pageable pageable) {
        return postQueryRepository.findPagePost(condition, pageable);
    }

    @Transactional(readOnly = true)
    public Post findPost(long postId) {
        PostEntity postEntity = postEntityRepository
                .findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        return new Post(postEntity);
    }
}
