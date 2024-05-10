package io.woorinpang.postservice.core.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentEntityRepository extends JpaRepository<PostCommentEntity, Long> {
}
