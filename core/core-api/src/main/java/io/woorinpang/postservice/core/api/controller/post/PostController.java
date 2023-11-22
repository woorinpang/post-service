package io.woorinpang.postservice.core.api.controller.post;

import io.woorinpang.postservice.core.domain.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity addPost(
            @RequestBody @Valid AddPostRequest request
    ) {
        long successId = postService.addPost(request.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(successId);
    }
}
