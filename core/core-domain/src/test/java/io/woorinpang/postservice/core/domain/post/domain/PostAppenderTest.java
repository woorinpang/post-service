package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.RepositoryTest;
import io.woorinpang.postservice.core.domain.post.repository.FindPagePostProjection;
import io.woorinpang.postservice.core.domain.post.repository.PostEntity;
import io.woorinpang.postservice.core.domain.post.repository.PostQueryRepository;
import io.woorinpang.postservice.core.domain.post.repository.PostSearchCondition;
import io.woorinpang.postservice.core.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[레포지토리 테스트] PostAppenderTest")
class PostAppenderTest extends RepositoryTest {
    @Autowired private PostAppender postAppender;

    @DisplayName("게시글을 저장하면 성공한다")
    @Test
    void append() {
        //Arrange
        User user = new User(1L);
        PostContent postContent = new PostContent("제목", "내용");

        //Act
        long appendedId = postAppender.append(user, postContent);

        //Assert
        PostEntity findPostEntity = em.find(PostEntity.class, appendedId);
        assertThat(findPostEntity).isNotNull();
        assertThat(findPostEntity.getTitle()).isEqualTo(postContent.title());
        assertThat(findPostEntity.getContent()).isEqualTo(postContent.content());
        assertThat(findPostEntity.getCreatedDate()).isNotNull();
        assertThat(findPostEntity.getCreatedBy()).isEqualTo(String.valueOf(user.id()));
    }
}