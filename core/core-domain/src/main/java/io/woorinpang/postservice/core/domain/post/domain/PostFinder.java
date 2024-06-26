package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.FindPagePostProjection;
import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;
import io.woorinpang.postservice.core.domain.post.repository.PostQueryRepository;
import io.woorinpang.postservice.core.domain.post.repository.PostSearchCondition;
import io.woorinpang.postservice.core.domain.support.model.CommonPage;
import io.woorinpang.postservice.core.domain.support.model.CommonPageInfo;
import io.woorinpang.postservice.core.domain.support.model.CommonPageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static io.woorinpang.postservice.core.domain.post.domain.PostEntityRepositoryHelper.findPostEntityById;

@Component
@RequiredArgsConstructor
public class PostFinder {
    private final PostQueryRepository postQueryRepository;
    private final PostEntityRepository postEntityRepository;

    @Transactional(readOnly = true)
    public CommonPage<FindPagePostProjection> findPagePost(PostSearchCondition condition, CommonPageable pageable) {
        Page<FindPagePostProjection> content = postQueryRepository.findPagePost(condition, pageable.toPageable());
        return new CommonPage<>(
                content.getContent(),
                new CommonPageInfo(content.getNumber(), content.getSize(), content.getTotalElements(), content.getTotalPages(), content.isFirst(), content.isLast())
        );
    }

    @Transactional(readOnly = true)
    public Post findPost(PostTarget target) {
        return new Post(findPostEntityById(postEntityRepository, target.postId()));
    }
}
