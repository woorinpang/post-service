package com.woorinpang.postservice.core.post.presentation;

import com.woorinpang.postservice.core.post.PostSetup;
import com.woorinpang.postservice.core.post.application.PostService;
import com.woorinpang.postservice.core.post.presentation.dto.request.SavePostRequest;
import com.woorinpang.postservice.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends IntegrationTest {

    @Autowired
    protected PostService postService;

    @Test
    void savePost() throws Exception {
        //given
        SavePostRequest request = PostSetup.setSavePostRequest();

        //expected
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.message").value(HttpStatus.CREATED.getReasonPhrase()))
                .andExpect(jsonPath("$.status").value(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.data.savedPostId").hasJsonPath())
                .andDo(print());
    }
}