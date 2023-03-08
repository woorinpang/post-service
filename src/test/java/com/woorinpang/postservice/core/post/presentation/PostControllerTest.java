package com.woorinpang.postservice.core.post.presentation;

import com.woorinpang.postservice.core.post.application.PostService;
import com.woorinpang.postservice.core.post.application.dto.PostCommandMapper;
import com.woorinpang.postservice.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PostControllerTest extends IntegrationTest {

    @Autowired protected PostService postService;
    @Autowired protected PostCommandMapper mapper;

    @Test
    void savePost() {

    }
}