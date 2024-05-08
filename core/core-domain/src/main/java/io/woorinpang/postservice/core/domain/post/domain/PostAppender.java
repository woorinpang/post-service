package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.PostEntity;
import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;
import io.woorinpang.postservice.core.domain.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostAppender {
    private final PostEntityRepository postRepository;

    @Transactional
    public long append(LoginUser user, PostContent postContent) {
        return postRepository.save(toEntity(user, postContent)).getId();
    }

    private static PostEntity toEntity(LoginUser user, PostContent postContent) {
        return PostEntity.builder()
                .userId(user.id())
                .title(postContent.title())
                .content(postContent.content())
                .build();
    }
}
