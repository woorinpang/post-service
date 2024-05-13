package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.PostCommentEntity;
import io.woorinpang.postservice.core.domain.post.repository.PostCommentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static io.woorinpang.postservice.core.domain.post.domain.PostCommentEntityHelper.*;

@Component
@RequiredArgsConstructor
public class PostCommentRemover {
    private final PostCommentEntityRepository postCommentEntityRepository;

    @Transactional
    public void remove(PostCommentTarget target) {
        PostCommentEntity findPostComment = findPostCommentById(postCommentEntityRepository, target.commentId());
        postCommentEntityRepository.delete(findPostComment);
    }
}
