package io.woorinpang.postservice.core.domain.post;

import io.woorinpang.postservice.core.domain.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostAppender postAppender;

    public long addPost(LoginUser user, PostContent postContent) {
        return postAppender.append(user, postContent);
    }
}
