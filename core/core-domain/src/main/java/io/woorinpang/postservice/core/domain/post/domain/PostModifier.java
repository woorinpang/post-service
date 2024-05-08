package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static io.woorinpang.postservice.core.domain.post.domain.PostEntityRepositoryHelper.findPostEntityById;

@Component
@RequiredArgsConstructor
public class PostModifier {
    private final PostEntityRepository postEntityRepository;

    @Transactional
    public void modify(long postId, String title, String content) {
        findPostEntityById(postEntityRepository, postId)
                .modify(title, content);
    }
}
