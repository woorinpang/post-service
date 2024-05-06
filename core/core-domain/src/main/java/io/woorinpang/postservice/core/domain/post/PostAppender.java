package io.woorinpang.postservice.core.domain.post;

import io.woorinpang.postservice.core.domain.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostAppender {
    private final PostRepository postRepository;

    public long append(LoginUser user, PostContent postContent) {
        return postRepository.save(toEntity(user, postContent)).getId();
    }

    private static Post toEntity(LoginUser user, PostContent postContent) {
        return Post.builder()
                .userId(user.id())
                .title(postContent.title())
                .content(postContent.content())
                .build();
    }
}
