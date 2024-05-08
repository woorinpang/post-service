package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static io.woorinpang.postservice.core.domain.post.domain.PostEntityRepositoryHelper.findPostEntityById;

@Component
@RequiredArgsConstructor
public class PostRemover {
    private final PostEntityRepository postEntityRepository;

    public void delete(long postId) {
        findPostEntityById(postEntityRepository, postId)
                .delete();
    }
}
