package io.woorinpang.postservice.core.domain.post.application;

import io.woorinpang.postservice.core.domain.MockTest;
import io.woorinpang.postservice.core.domain.post.domain.PostAppender;
import io.woorinpang.postservice.core.domain.post.domain.PostContent;
import io.woorinpang.postservice.core.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("[서비스 테스트] PostServiceTest")
class PostServiceTest extends MockTest {
    @InjectMocks private PostService postService;
    @Mock private PostAppender postAppender;

    @DisplayName("게시글 저장하면 성공한다")
    @Test
    void addPost() {
        //Arrange
        User user = new User(1L);
        PostContent postContent = new PostContent("제목", "내용");
        given(postAppender.append(any(User.class), any(PostContent.class))).willReturn(1L);

        //Act
        long appendedId = postService.addPost(user, postContent);

        //Assert
        assertThat(appendedId).isEqualTo(1L);
        verify(postAppender, times(1)).append(any(User.class), any(PostContent.class));
    }
}