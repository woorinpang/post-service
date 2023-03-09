package com.woorinpang.postservice.core.post;

import com.woorinpang.postservice.core.post.infrastructure.PostQueryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class PostTestConfig {

    @PersistenceContext protected EntityManager em;

    @Bean
    protected PostQueryRepository postQueryRepository() {
        return new PostQueryRepository(em);
    }
}
