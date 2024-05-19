package io.woorinpang.postservice.core.domain.post.application;

import io.woorinpang.postservice.core.domain.MockTest;
import io.woorinpang.postservice.core.domain.post.domain.PostAppender;
import io.woorinpang.postservice.core.domain.post.domain.PostContent;
import io.woorinpang.postservice.core.domain.post.domain.PostFinder;
import io.woorinpang.postservice.core.domain.post.repository.FindPagePostProjection;
import io.woorinpang.postservice.core.domain.post.repository.PostSearchCondition;
import io.woorinpang.postservice.core.domain.support.model.CommonPage;
import io.woorinpang.postservice.core.domain.support.model.CommonPageInfo;
import io.woorinpang.postservice.core.domain.support.model.CommonPageable;
import io.woorinpang.postservice.core.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("[서비스 테스트] PostServiceTest")
class PostServiceTest extends MockTest {
    @InjectMocks private PostService postService;
    @Mock private PostAppender postAppender;
    @Mock private PostFinder postFinder;

    @DisplayName("")
    @Test
    void findPosts() {
        //given
        List<FindPagePostProjection> findPagePostProjections = List.of(
                new FindPagePostProjection(1, "제목", LocalDateTime.now()),
                new FindPagePostProjection(1, "제목", LocalDateTime.now()),
                new FindPagePostProjection(1, "제목", LocalDateTime.now()),
                new FindPagePostProjection(1, "제목", LocalDateTime.now()),
                new FindPagePostProjection(1, "제목", LocalDateTime.now())
        );
        PageImpl<FindPagePostProjection> projections = new PageImpl<>(findPagePostProjections);

        CommonPageInfo commonPageInfo = new CommonPageInfo(projections.getNumber(), projections.getSize(), projections.getTotalElements(), projections.getTotalPages(), projections.isFirst(), projections.isLast());
        given(postFinder.findPagePost(any(), any())).willReturn(new CommonPage<>(projections.getContent(), commonPageInfo));

        PostSearchCondition condition = new PostSearchCondition(null, null);
        CommonPageable pageable = new CommonPageable(0, 20);

        //when
        CommonPage<FindPagePostProjection> content = postService.findPagePost(condition, pageable);

        //then
        Assertions.assertThat(content).isNotNull();
    }

    @DisplayName("게시글 저장하면 성공한다")
    @Test
    void addPost() {
        //Arrange
        User user = new User(1L);
        PostContent postContent = new PostContent("제목", "내용", "작성자");
        given(postAppender.append(any(User.class), any(PostContent.class))).willReturn(1L);

        //Act
        long appendedId = postService.addPost(user, postContent);

        //Assert
        assertThat(appendedId).isEqualTo(1L);
        verify(postAppender, times(1)).append(any(User.class), any(PostContent.class));
    }
}