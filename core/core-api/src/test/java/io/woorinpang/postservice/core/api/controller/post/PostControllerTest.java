package io.woorinpang.postservice.core.api.controller.post;

import io.restassured.http.ContentType;
import io.woorinpang.postservice.core.api.controller.post.request.AddPostRequest;
import io.woorinpang.postservice.core.domain.post.application.PostService;
import io.woorinpang.postservice.core.domain.post.repository.FindPagePostProjection;
import io.woorinpang.test.api.RestDocsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static io.woorinpang.test.api.RestDocsUtils.requestPreprocessor;
import static io.woorinpang.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

class PostControllerTest extends RestDocsTest {
    private PostService postService;
    private PostController controller;
    protected MockMvc mockMvcTemp;


    @BeforeEach
    void setup() {
        postService = mock(PostService.class);
        controller = new PostController(postService);
        mockMvc = mockController(controller);
    }

    @Test
    void findPagePost() throws Exception {
        List<FindPagePostProjection> projection = List.of(new FindPagePostProjection(1L, "title", 1L, LocalDateTime.now()));
        when(postService.findPagePost(any(), any())).thenReturn(new PageImpl<>(projection));

        given().contentType(ContentType.JSON)
                .queryParam("title", "title")
                .queryParam("content", "content")
                .queryParam("page", 0)
                .queryParam("size", 20)
                .get("/posts")
                .then()
                .status(HttpStatus.OK)
                .apply(
                        document(
                                "findPagePost",
                                requestPreprocessor(),
                                responsePreprocessor(),
                                queryParameters(
                                        parameterWithName("title")
                                                .description("검색 title"),
                                        parameterWithName("content")
                                                .description("검색 content"),
                                        parameterWithName("page")
                                                .description("페이지"),
                                        parameterWithName("size")
                                                .description("페이지 크기")
                                ),
                                responseFields(
                                        fieldWithPath("result")
                                                .type(JsonFieldType.STRING)
                                                .description("결과 유형(SUCCESS/ERROR"),
                                        fieldWithPath("data.id")
                                                .type(JsonFieldType.NUMBER)
                                                .description("생성된 Post 고유번호"),
                                        fieldWithPath("error")
                                                .type(JsonFieldType.STRING)
                                                .ignored())));
    }

    @Test
    void findPost() {
    }

    @Test
    void addPost() {
        when(postService.addPost(any(), any())).thenReturn(1L);

        given().contentType(ContentType.JSON)
                .body(new AddPostRequest("title", "content"))
                .post("/posts")
                .then()
                .status(HttpStatus.CREATED)
                .apply(
                        document(
                                "addPost",
                                requestPreprocessor(),
                                responsePreprocessor(),
                                requestFields(
                                        fieldWithPath("title")
                                                .type(JsonFieldType.STRING)
                                                .description("제목"),
                                        fieldWithPath("content")
                                                .type(JsonFieldType.STRING)
                                                .description("내용")
                                ),
                                responseFields(
                                        fieldWithPath("result")
                                                .type(JsonFieldType.STRING)
                                                .description("결과 유형(SUCCESS/ERROR"),
                                        fieldWithPath("data.id")
                                                .type(JsonFieldType.NUMBER)
                                                .description("생성된 Post 고유번호"),
                                        fieldWithPath("error")
                                                .type(JsonFieldType.STRING)
                                                .ignored())));
    }
}