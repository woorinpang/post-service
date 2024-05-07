package io.woorinpang.postservice.core.domain.post.application;

import io.woorinpang.postservice.core.domain.post.domain.*;
import io.woorinpang.postservice.core.domain.post.repository.FindPagePostProjection;
import io.woorinpang.postservice.core.domain.post.repository.PostSearchCondition;
import io.woorinpang.postservice.core.domain.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostAppender postAppender;
    private final PostFinder postFinder;
    private final PostRemover postRemover;

    public Page<FindPagePostProjection> findPagePost(PostSearchCondition condition, Pageable pageable) {
        return postFinder.findPagePost(condition, pageable);
    }

    public Post findPost(long postId) {
        return postFinder.findPost(postId);
    }

    public long addPost(LoginUser user, PostContent postContent) {
        return postAppender.append(user, postContent);
    }

    public void deletePost(long postId) {
        postRemover.delete(postId);
    }
}
