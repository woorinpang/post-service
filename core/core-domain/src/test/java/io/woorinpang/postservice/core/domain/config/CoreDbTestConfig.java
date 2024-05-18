package io.woorinpang.postservice.core.domain.config;

import io.woorinpang.postservice.core.domain.post.domain.PostAppender;
import io.woorinpang.postservice.core.domain.post.domain.PostFinder;
import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;
import io.woorinpang.postservice.core.domain.post.repository.PostQueryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CoreDbTestConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public PostAppender postAppender(PostEntityRepository postEntityRepository) {
        return new PostAppender(postEntityRepository);
    }

    @Bean
    public PostFinder postFinder(PostEntityRepository postEntityRepository) {
        return new PostFinder(postQueryRepository(), postEntityRepository);
    }

    @Bean
    public PostQueryRepository postQueryRepository() {
        return new PostQueryRepository(entityManager);
    }
}
