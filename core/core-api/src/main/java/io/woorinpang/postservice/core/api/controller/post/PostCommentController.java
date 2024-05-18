package io.woorinpang.postservice.core.api.controller.post;

import io.woorinpang.postservice.core.api.controller.post.request.AddCommentRequest;
import io.woorinpang.postservice.core.api.controller.post.response.FindPagePostCommentResponse;
import io.woorinpang.postservice.core.api.support.response.ApiResponse;
import io.woorinpang.postservice.core.api.support.response.DefaultIdResponse;
import io.woorinpang.postservice.core.domain.post.application.PostCommentService;
import io.woorinpang.postservice.core.domain.post.domain.PostCommentTarget;
import io.woorinpang.postservice.core.domain.post.domain.PostTarget;
import io.woorinpang.postservice.core.domain.support.model.AuthenticatedUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class PostCommentController {
    private final PostCommentService postCommentService;

    /**
     * postComment 목록조회
     */
    @GetMapping
    public ResponseEntity<ApiResponse<?>> findComments(
            @PathVariable("postId") long postId,
            @PageableDefault(page = 0, size = 20) Pageable pageable
    ) {
        Page<FindPagePostCommentResponse> response =
                postCommentService.findPagePostComment(new PostTarget(postId), pageable).map(FindPagePostCommentResponse::new);
        return ResponseEntity.ok().body(ApiResponse.success(response));
    }

    /**
     * postComment 추가
     */
    @PostMapping
    public ResponseEntity<ApiResponse<DefaultIdResponse>> addComment(
            @PathVariable("postId") long postId,
            @RequestBody @Valid AddCommentRequest request,
            AuthenticatedUser authenticatedUser) {
        long successId = postCommentService.addPostComment(new PostTarget(postId), request.toCommand(), authenticatedUser.toUser());
        return ResponseEntity.ok().body(ApiResponse.success(new DefaultIdResponse(successId)));
    }

    /**
     * postComment 삭제
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<?>> deleteComment(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId
    ) {
        postCommentService.deletePostComment(new PostCommentTarget(postId, commentId));
        return ResponseEntity.ok().body(ApiResponse.success());
    }
}
