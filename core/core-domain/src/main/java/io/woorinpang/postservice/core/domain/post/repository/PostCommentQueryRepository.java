package io.woorinpang.postservice.core.domain.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.woorinpang.postservice.core.domain.post.repository.QPostCommentEntity.postCommentEntity;

@Repository
@RequiredArgsConstructor
public class PostCommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<FindPagePostCommentProjection> findPagePostComment(long postId, Pageable pageable) {
        return PageableExecutionUtils.getPage(
                getPostCommentList(postId, pageable),
                pageable,
                getPostCommentListCount(postId)::fetchOne
        );
    }

    private List<FindPagePostCommentProjection> getPostCommentList(long postId, Pageable pageable) {
        return queryFactory
                .select(Projections.constructor(FindPagePostCommentProjection.class,
                        postCommentEntity.author,
                        postCommentEntity.content,
                        postCommentEntity.createdDate
                ))
                .from(postCommentEntity)
                .where(
                        postCommentEntity.postId.eq(postId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private JPAQuery<Long> getPostCommentListCount(long postId) {
        return queryFactory
                .select(postCommentEntity.count())
                .from(postCommentEntity)
                .where(
                        postCommentEntity.postId.eq(postId)
                );
    }
}
