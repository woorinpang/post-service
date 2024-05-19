package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.FindPagePostCommentProjection;
import io.woorinpang.postservice.core.domain.post.repository.PostCommentQueryRepository;
import io.woorinpang.postservice.core.domain.support.model.CommonPage;
import io.woorinpang.postservice.core.domain.support.model.CommonPageInfo;
import io.woorinpang.postservice.core.domain.support.model.CommonPageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostCommentFinder {
    private final PostCommentQueryRepository postCommentQueryRepository;

    @Transactional(readOnly = true)
    public CommonPage<FindPagePostCommentProjection> findPagePostComment(PostTarget target, CommonPageable pageable) {
        Page<FindPagePostCommentProjection> content = postCommentQueryRepository.findPagePostComment(target.postId(), pageable.toPageable());
        return new CommonPage<>(
                content.getContent(),
                new CommonPageInfo(content.getNumber(), content.getSize(), content.getTotalElements(), content.getTotalPages(), content.isFirst(), content.isLast())
        );
    }
}
