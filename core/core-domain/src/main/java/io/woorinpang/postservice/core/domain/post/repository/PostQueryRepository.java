package io.woorinpang.postservice.core.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class PostQueryRepository {
    private final JPAQueryFactory queryFactory;

    public PostQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public void findPagePost() {

    }
}
