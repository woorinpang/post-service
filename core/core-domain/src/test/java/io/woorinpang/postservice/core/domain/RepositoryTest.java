package io.woorinpang.postservice.core.domain;

import io.woorinpang.postservice.core.domain.config.JpaAuditingConfig;
import io.woorinpang.postservice.core.domain.config.P6SpyConfig;
import io.woorinpang.postservice.core.domain.config.QuerydslConfig;
import io.woorinpang.postservice.core.domain.post.domain.PostAppender;
import io.woorinpang.postservice.core.domain.post.repository.PostEntityRepository;
import io.woorinpang.postservice.core.domain.post.repository.PostQueryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Tag("repository")
@Import(RepositoryTest.CoreDbTestConfig.class)
@ActiveProfiles("local")
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {QuerydslConfig.class, JpaAuditingConfig.class, P6SpyConfig.class}
))
public abstract class RepositoryTest {
    @PersistenceContext protected EntityManager em;

    @TestConfiguration
    protected static class CoreDbTestConfig {
        @PersistenceContext
        private EntityManager entityManager;

        @Bean
        public PostAppender postAppender(PostEntityRepository postEntityRepository) {
            return new PostAppender(postEntityRepository);
        }

        @Bean
        public PostQueryRepository postQueryRepository() {
            return new PostQueryRepository(entityManager);
        }
    }
}
