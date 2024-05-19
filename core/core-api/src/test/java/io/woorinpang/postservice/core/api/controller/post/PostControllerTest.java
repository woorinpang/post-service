package io.woorinpang.postservice.core.api.controller.post;

import io.restassured.http.ContentType;
import io.woorinpang.postservice.core.api.controller.post.request.AddPostRequest;
import io.woorinpang.postservice.core.domain.post.application.PostService;
import io.woorinpang.postservice.core.domain.post.repository.FindPagePostProjection;
import io.woorinpang.postservice.core.domain.support.model.CommonPage;
import io.woorinpang.postservice.core.domain.support.model.CommonPageInfo;
import io.woorinpang.test.api.RestDocsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

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


    @BeforeEach
    void setup() {
        postService = mock(PostService.class);
        controller = new PostController(postService);
        mockMvc = mockController(controller);
    }

    @Test
    void findPagePost() {
        List<FindPagePostProjection> projection = List.of(
                new FindPagePostProjection(1L, "title", LocalDateTime.now()),
                new FindPagePostProjection(1L, "title", LocalDateTime.now()),
                new FindPagePostProjection(1L, "title", LocalDateTime.now())
        );

        CommonPage<FindPagePostProjection> response = new CommonPage<>(
                projection,
                new CommonPageInfo(0, 20, projection.size(), 1, true, true)
        );

        when(postService.findPagePost(any(), any())).thenReturn(response);

        given().contentType(ContentType.JSON)
                .queryParam("title", "title")
                .queryParam("content", "content")
                .queryParam("page", 0)
                .queryParam("size", 20)
                .get("/v1/posts")
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
                                        fieldWithPath("error")
                                                .type(JsonFieldType.STRING)
                                                .ignored(),
                                        fieldWithPath("data.content[*].postId")
                                                .type(JsonFieldType.NUMBER)
                                                .description("Post 고유번호"),
                                        fieldWithPath("data.content[*].title")
                                                .type(JsonFieldType.STRING)
                                                .description("제목"),
                                        fieldWithPath("data.content[*].createdDate")
                                                .type(JsonFieldType.STRING)
                                                .description("생성일자"),
                                        fieldWithPath("data.page.number")
                                                .type(JsonFieldType.NUMBER)
                                                .description("페이지"),
                                        fieldWithPath("data.page.size")
                                                .type(JsonFieldType.NUMBER)
                                                .description("사이즈"),
                                        fieldWithPath("data.page.totalElements")
                                                .type(JsonFieldType.NUMBER)
                                                .description("전체 건수"),
                                        fieldWithPath("data.page.totalPages")
                                                .type(JsonFieldType.NUMBER)
                                                .description("전체 페이지"),
                                        fieldWithPath("data.page.isFirst")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("처음 여부"),
                                        fieldWithPath("data.page.isLast")
                                                .type(JsonFieldType.BOOLEAN)
                                                .description("마지막 여부"))));
    }

    @Test
    void findPost() {
    }

    @Test
    void addPost() {
        when(postService.addPost(any(), any())).thenReturn(1L);

        given().contentType(ContentType.JSON)
                .body(new AddPostRequest("제목", "내용", "작성자"))
                .post("/v1/posts")
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
                                                .description("내용"),
                                        fieldWithPath("author")
                                                .type(JsonFieldType.STRING)
                                                .description("작성자")
                                ),
                                responseFields(
                                        fieldWithPath("result")
                                                .type(JsonFieldType.STRING)
                                                .description("결과 유형(SUCCESS/ERROR"),
                                        fieldWithPath("data.id")
                                                .type(JsonFieldType.NUMBER)
                                                .description("성공 아이디"),
                                        fieldWithPath("error")
                                                .type(JsonFieldType.STRING)
                                                .ignored())));
    }
}