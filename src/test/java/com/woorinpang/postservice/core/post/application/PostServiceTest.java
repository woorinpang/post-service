package com.woorinpang.postservice.core.post.application;

import com.woorinpang.postservice.core.post.PostSetup;
import com.woorinpang.postservice.core.post.application.dto.PostCommandMapper;
import com.woorinpang.postservice.core.post.application.dto.command.SavePostCommand;
import com.woorinpang.postservice.core.post.domain.Post;
import com.woorinpang.postservice.core.post.domain.PostRepository;
import com.woorinpang.postservice.core.post.infrastructure.PostQueryRepository;
import com.woorinpang.postservice.test.MockTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PostServiceTest extends MockTest {

    @InjectMocks protected PostService postService;
    @Mock protected PostRepository postRepository;
    @Mock protected PostCommandMapper mapper;

    @Nested
    @DisplayName("게시물_저장_")
    class save {
        @Test
        void 성공한다() {
            //given
            Post post = PostSetup.setPost();
            SavePostCommand command = PostSetup.setSavePostCommand();

            given(postRepository.save(any(Post.class))).willReturn(post);
            given(mapper.toPost(any(SavePostCommand.class))).willReturn(post);

            //when
            Long savedId = postService.save(command);

            //then
            assertThat(post.getTitle()).isEqualTo(PostSetup.TITLE);
            assertThat(post.getContent()).isEqualTo(PostSetup.CONTENT);
            assertThat(post.getHits()).isZero();

            //verify
            verify(mapper, times(1)).toPost(any(SavePostCommand.class));
            verify(postRepository, times(1)).save(any(Post.class));
        }
    }
}