package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;
import io.woorinpang.postservice.core.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static io.woorinpang.postservice.core.domain.post.domain.PostEntityRepositoryHelper.findPostEntityById;

@Component
@RequiredArgsConstructor
public class PostRemover {
    private final PostEntityRepository postEntityRepository;

    @Transactional
    public void delete(PostTarget target, User user) {
        findPostEntityById(postEntityRepository, target.postId())
                .delete(user.id());
    }
}
