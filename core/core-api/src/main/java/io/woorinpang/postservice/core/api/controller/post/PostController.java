package io.woorinpang.postservice.core.api.controller.post;

import io.woorinpang.postservice.core.api.controller.post.param.FindPagePostSearchParam;
import io.woorinpang.postservice.core.api.controller.post.request.AddPostRequest;
import io.woorinpang.postservice.core.api.controller.post.request.ModifyPostRequest;
import io.woorinpang.postservice.core.api.controller.post.response.FindPagePostResponse;
import io.woorinpang.postservice.core.api.controller.post.response.FindPostResponse;
import io.woorinpang.postservice.core.api.support.response.ApiResponse;
import io.woorinpang.postservice.core.api.support.response.DefaultIdResponse;
import io.woorinpang.postservice.core.domain.post.application.PostService;
import io.woorinpang.postservice.core.domain.post.domain.Post;
import io.woorinpang.postservice.core.domain.user.LoginUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    /**
     * Post 목록조회
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<FindPagePostResponse>>> findPagePost(
            FindPagePostSearchParam param,
            @PageableDefault(page = 0, size = 20) Pageable pageable
    ) {
        Page<FindPagePostResponse> response =
                postService.findPagePost(param.toPostSearchCondition(), pageable).map(FindPagePostResponse::new);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
    }

    /**
     * Post 단건조회
     */
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<FindPostResponse>> findPost(
            @PathVariable long postId
    ) {
        Post post = postService.findPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(new FindPostResponse(post)));
    }

    /**
     * Post 저장
     */
    @PostMapping
    public ResponseEntity<ApiResponse<DefaultIdResponse>> addPost(
            @RequestBody @Valid AddPostRequest request
    ) {
        //TODO apigateway 에서 유저 정보를 responseBody 담아 보내야 함
        long userId = 1L;
        long successId = postService.addPost(new LoginUser(userId), request.toPostContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(new DefaultIdResponse(successId)));
    }

    /**
     * Post 수정
     */
    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse> modifyPost(
            @PathVariable long postId,
            @RequestBody @Valid ModifyPostRequest request
    ) {
        postService.modifyPost(postId, request.getTitle(), request.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success());
    }

    /**
     * Post 삭제
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> deletePost(
            @PathVariable long postId
    ) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success());
    }
}
