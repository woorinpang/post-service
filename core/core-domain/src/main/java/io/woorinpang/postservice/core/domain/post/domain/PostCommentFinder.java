package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.FindPagePostCommentProjection;
import io.woorinpang.postservice.core.domain.post.repository.PostCommentQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCommentFinder {
    private final PostCommentQueryRepository postCommentQueryRepository;

    public Page<FindPagePostCommentProjection> findPagePostComment(PostTarget target, Pageable pageable) {
        return postCommentQueryRepository.findPagePostComment(target.postId(), pageable);
    }
}
