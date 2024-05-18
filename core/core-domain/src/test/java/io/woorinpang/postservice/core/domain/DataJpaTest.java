package io.woorinpang.postservice.core.domain;

import io.woorinpang.postservice.core.domain.config.CoreDbTestConfig;
import io.woorinpang.postservice.core.domain.config.JpaAuditingConfig;
import io.woorinpang.postservice.core.domain.config.P6SpyConfig;
import io.woorinpang.postservice.core.domain.config.QuerydslConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Tag;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Tag("dataJpa")
@Import(CoreDbTestConfig.class)
@ActiveProfiles("local")
@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {QuerydslConfig.class, JpaAuditingConfig.class, P6SpyConfig.class}
))
public abstract class DataJpaTest {
    @PersistenceContext protected EntityManager em;
}
