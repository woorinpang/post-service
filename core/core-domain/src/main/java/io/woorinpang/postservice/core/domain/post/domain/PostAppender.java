package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.post.repository.PostEntity;
import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;
import io.woorinpang.postservice.core.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostAppender {
    private final PostEntityRepository postEntityRepository;

    @Transactional
    public long append(User user, PostContent postContent) {
        return postEntityRepository.save(toEntity(user, postContent)).getId();
    }

    private static PostEntity toEntity(User user, PostContent postContent) {
        return PostEntity.builder()
                .userId(user.id())
                .title(postContent.title())
                .content(postContent.content())
                .author(postContent.author())
                .build();
    }
}
