package io.woorinpang.postservice.core.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostFinder {
    private final PostQueryRepository postQueryRepository;

    public void findPagePost() {
        postQueryRepository.findPagePost();
    }
}
