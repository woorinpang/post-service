package io.woorinpang.postservice.core.domain.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.woorinpang.postservice.core.domain.post.repository.QPostEntity.postEntity;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class PostQueryRepository {
    private final JPAQueryFactory queryFactory;

    public PostQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Page<FindPagePostProjection> findPagePost(PostSearchCondition condition, Pageable pageable) {
        return PageableExecutionUtils.getPage(
                getFindPagePostProjectionList(condition, pageable),
                pageable,
                getPostListCount(condition)::fetchOne
        );
    }

    private List<FindPagePostProjection> getFindPagePostProjectionList(PostSearchCondition condition, Pageable pageable) {
        return queryFactory
                .select(Projections.constructor(FindPagePostProjection.class,
                        postEntity.id,
                        postEntity.title,
                        postEntity.content,
                        postEntity.userId))
                .from(postEntity)
                .where(
                        searchTitleContainsIgnoreCase(condition.getTitle()),
                        searchContentContainsIgnoreCase(condition.getContent())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(postEntity.id.desc())
                .fetch();
    }

    private JPAQuery<Long> getPostListCount(PostSearchCondition condition) {
        return queryFactory
                .select(postEntity.count())
                .from(postEntity)
                .where(
                        searchTitleContainsIgnoreCase(condition.getTitle()),
                        searchContentContainsIgnoreCase(condition.getContent())
                );
    }

    private BooleanExpression searchTitleContainsIgnoreCase(String title) {
        return !hasText(title) ? null : postEntity.title.containsIgnoreCase(title);
    }

    private BooleanExpression searchContentContainsIgnoreCase(String content) {
        return !hasText(content) ? null : postEntity.title.containsIgnoreCase(content);
    }
}
