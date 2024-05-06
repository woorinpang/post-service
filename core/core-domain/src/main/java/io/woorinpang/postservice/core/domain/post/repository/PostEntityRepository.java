package io.woorinpang.postservice.core.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostEntityRepository extends JpaRepository<PostEntity, Long> {
}
