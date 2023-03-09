package com.woorinpang.postservice.core.post.presentation;

import com.woorinpang.postservice.core.post.PostSetup;
import com.woorinpang.postservice.core.post.application.PostService;
import com.woorinpang.postservice.core.post.presentation.dto.request.SavePostRequest;
import com.woorinpang.postservice.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class PostControllerTest extends IntegrationTest {

    @Autowired
    protected PostService postService;
    //@Autowired protected PostCommandMapper mapper;

    @Test
    void savePost() throws Exception {
        //given
        SavePostRequest request = PostSetup.setSavePostRequest();

        //expected
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}