package io.woorinpang.postservice.core.api.controller.post;

import io.woorinpang.postservice.core.api.controller.post.request.AddCommentRequest;
import io.woorinpang.postservice.core.api.support.response.ApiResponse;
import io.woorinpang.postservice.core.api.support.response.DefaultIdResponse;
import io.woorinpang.postservice.core.domain.post.application.PostCommentService;
import io.woorinpang.postservice.core.domain.post.domain.PostTarget;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class PostCommentController {
    private final PostCommentService postCommentService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> findComments(
            @PathVariable("postId") long postId,
            @PageableDefault(page = 0, size = 20) Pageable pageable
    ) {
        return ResponseEntity.ok().body(ApiResponse.success());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DefaultIdResponse>> addComment(
            @PathVariable("postId") long postId,
            @RequestBody @Valid AddCommentRequest request) {
        long successId = postCommentService.addPostComment(new PostTarget(postId), request.toCommand());
        return ResponseEntity.ok().body(ApiResponse.success(new DefaultIdResponse(successId)));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId
    ) {
        return ResponseEntity.ok().body(ApiResponse.success());
    }
}
