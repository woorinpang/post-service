package io.woorinpang.postservice.core.domain.post.domain;

import io.woorinpang.postservice.core.domain.DataJpaTest;
import io.woorinpang.postservice.core.domain.post.repository.FindPagePostProjection;
import io.woorinpang.postservice.core.domain.post.repository.PostEntity;
import io.woorinpang.postservice.core.domain.post.repository.PostSearchCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class PostFinderTest extends DataJpaTest {
    @Autowired private PostFinder postFinder;

    @DisplayName("게시글 0페이지 조회하면 전체수=100, 전체페이지=10, 현재페이지=0 반환한다")
    @Test
    void findPagePost() {
        //given
        long userId = 1L;
        IntStream.range(0, 100).forEach(i -> {
            PostEntity postEntity = new PostEntity("제목" + i, "내용" + i, "작성자", userId);
            em.persist(postEntity);
        });

        PostSearchCondition condition = new PostSearchCondition("제목", null);
        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        Page<FindPagePostProjection> posts = postFinder.findPagePost(condition, pageRequest);

        //then
        assertThat(posts.getTotalElements()).isEqualTo(100);
        assertThat(posts.getTotalPages()).isEqualTo(10);
        assertThat(posts.getNumber()).isEqualTo(0);
    }

    @DisplayName("게시글 조회하면 Post 객체로 반환한다")
    @Test
    void findPost() {
        //given
        long userId = 1L;

        PostEntity postEntity = new PostEntity("제목", "내용", "작성자", userId);
        em.persist(postEntity);

        PostTarget target = new PostTarget(postEntity.getId());

        //when
        Post findPost = postFinder.findPost(target);

        //then
        assertThat(findPost.getTitle()).isEqualTo(postEntity.getTitle());
        assertThat(findPost.getContent()).isEqualTo(postEntity.getContent());
        assertThat(findPost.getAuthor()).isEqualTo(postEntity.getAuthor());
        assertThat(findPost.getCreatedDate()).isNotNull();
        assertThat(findPost.getLastModifiedDate()).isNotNull();
    }
}