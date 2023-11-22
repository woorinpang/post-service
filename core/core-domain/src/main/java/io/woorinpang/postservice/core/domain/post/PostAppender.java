package io.woorinpang.postservice.core.domain.post;

import io.woorinpang.postservice.core.db.post.Post;
import io.woorinpang.postservice.core.db.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostAppender {
    private final PostRepository postRepository;

    public long append(String title) {
        Post post = new Post("title", "content");
        return postRepository.save(post).getId();
    }


}
