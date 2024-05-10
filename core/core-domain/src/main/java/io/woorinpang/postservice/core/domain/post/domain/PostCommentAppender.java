package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.PostCommentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostCommentAppender {
    private final PostCommentEntityRepository postCommentEntityRepository;

    @Transactional
    public long append(PostTarget target, AddCommentCommand comment) {
        return postCommentEntityRepository.save(comment.toPostCommentEntity(target.id())).getId();
    }
}
