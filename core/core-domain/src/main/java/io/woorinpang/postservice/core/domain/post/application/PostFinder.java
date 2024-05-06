package io.woorinpang.postservice.core.domain.post.application;

import io.woorinpang.postservice.core.domain.post.repository.PostEntity;
import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;
import io.woorinpang.postservice.core.domain.post.repository.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostFinder {
    private final PostQueryRepository postQueryRepository;
    private final PostEntityRepository postEntityRepository;

    @Transactional(readOnly = true)
    public void findPagePost() {
        postQueryRepository.findPagePost();
    }

    @Transactional(readOnly = true)
    public Post findPost(long postId) {
        PostEntity postEntity = postEntityRepository
                .findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        return new Post(postEntity);
    }
}
