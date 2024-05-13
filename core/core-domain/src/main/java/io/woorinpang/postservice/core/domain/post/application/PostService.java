package io.woorinpang.postservice.core.domain.post.application;

import io.woorinpang.postservice.core.domain.post.domain.*;
import io.woorinpang.postservice.core.domain.post.repository.FindPagePostProjection;
import io.woorinpang.postservice.core.domain.post.repository.PostSearchCondition;
import io.woorinpang.postservice.core.domain.user.User;
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
    private final PostModifier postModifier;

    public Page<FindPagePostProjection> findPagePost(PostSearchCondition condition, Pageable pageable) {
        return postFinder.findPagePost(condition, pageable);
    }

    public Post findPost(PostTarget target) {
        return postFinder.findPost(target);
    }

    public long addPost(User user, PostContent postContent) {
        return postAppender.append(user, postContent);
    }

    public void modifyPost(PostTarget target, String title, String content, User user) {
        postModifier.modify(target, title, content, user);
    }

    public void deletePost(PostTarget target) {
        postRemover.delete(target);
    }
}
