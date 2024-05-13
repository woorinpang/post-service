package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.FindPagePostProjection;
import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;
import io.woorinpang.postservice.core.domain.post.repository.PostQueryRepository;
import io.woorinpang.postservice.core.domain.post.repository.PostSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static io.woorinpang.postservice.core.domain.post.domain.PostEntityRepositoryHelper.findPostEntityById;

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
    public Post findPost(PostTarget target) {
        return new Post(findPostEntityById(postEntityRepository, target.postId()));
    }
}
