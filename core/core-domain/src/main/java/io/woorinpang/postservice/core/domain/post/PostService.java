package io.woorinpang.postservice.core.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostAppender postAppender;

    public long addPost(String title) {
        return postAppender.append(title);
    }
}
