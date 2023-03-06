package com.woorinpang.postservice.core.post.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    public PostQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
}
