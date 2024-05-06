package io.woorinpang.postservice.core.domain.post.application;

import io.woorinpang.postservice.core.domain.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostAppender postAppender;
    private final PostFinder postFinder;

    public long addPost(LoginUser user, PostContent postContent) {
        return postAppender.append(user, postContent);
    }

    public Post findPost(long postId) {
        return postFinder.findPost(postId);
    }
}
