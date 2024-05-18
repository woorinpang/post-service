package io.woorinpang.postservice.core.api.controller.post;

import io.woorinpang.postservice.core.api.controller.post.param.FindPagePostSearchParam;
import io.woorinpang.postservice.core.api.controller.post.request.AddPostRequest;
import io.woorinpang.postservice.core.api.controller.post.request.ModifyPostRequest;
import io.woorinpang.postservice.core.api.controller.post.response.FindPagePostResponse;
import io.woorinpang.postservice.core.api.controller.post.response.FindPostResponse;
import io.woorinpang.postservice.core.api.support.request.LoginUser;
import io.woorinpang.postservice.core.api.support.response.ApiResponse;
import io.woorinpang.postservice.core.api.support.response.DefaultIdResponse;
import io.woorinpang.postservice.core.domain.post.application.PostService;
import io.woorinpang.postservice.core.domain.post.domain.Post;
import io.woorinpang.postservice.core.domain.post.domain.PostTarget;
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
@RequestMapping("/v1/posts")
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
        Post post = postService.findPost(new PostTarget(postId));
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(new FindPostResponse(post)));
    }

    /**
     * Post 저장
     */
    @PostMapping
    public ResponseEntity<ApiResponse<DefaultIdResponse>> addPost(
            @ModelAttribute("loginUser") LoginUser loginUser,
            @RequestBody @Valid AddPostRequest request
    ) {
        long successId = postService.addPost(loginUser.toUser(), request.toPostContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(new DefaultIdResponse(successId)));
    }

    /**
     * Post 수정
     */
    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse> modifyPost(
            @ModelAttribute("loginUser") LoginUser loginUser,
            @PathVariable long postId,
            @RequestBody @Valid ModifyPostRequest request
    ) {
        postService.modifyPost(new PostTarget(postId), request.getTitle(), request.getContent(), loginUser.toUser());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success());
    }

    /**
     * Post 삭제
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> deletePost(
            @ModelAttribute("loginUser") LoginUser loginUser,
            @PathVariable long postId
    ) {
        postService.deletePost(new PostTarget(postId), loginUser.toUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success());
    }
}
