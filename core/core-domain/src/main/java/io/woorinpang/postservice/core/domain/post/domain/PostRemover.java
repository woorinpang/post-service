package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostRemover {
    private final PostEntityRepository postEntityRepository;

    public void delete(long postId) {
        postEntityRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"))
                .delete();
    }
}
