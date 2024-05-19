package io.woorinpang.postservice.core.domain.post.application;

import io.woorinpang.postservice.core.domain.post.domain.*;
import io.woorinpang.postservice.core.domain.post.repository.FindPagePostProjection;
import io.woorinpang.postservice.core.domain.post.repository.PostSearchCondition;
import io.woorinpang.postservice.core.domain.support.model.CommonPage;
import io.woorinpang.postservice.core.domain.support.model.CommonPageable;
import io.woorinpang.postservice.core.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostAppender postAppender;
    private final PostFinder postFinder;
    private final PostRemover postRemover;
    private final PostModifier postModifier;

    public CommonPage<FindPagePostProjection> findPagePost(PostSearchCondition condition, CommonPageable pageable) {
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

    public void deletePost(PostTarget target, User user) {
        postRemover.delete(target, user);
    }
}
