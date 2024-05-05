package io.woorinpang.postservice.core.api.controller.post;

import io.woorinpang.postservice.client.user.UserClient;
import io.woorinpang.postservice.client.user.model.FindUserClientResult;
import io.woorinpang.postservice.core.api.controller.post.request.AddPostRequest;
import io.woorinpang.postservice.core.domain.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final UserClient userClient;

    @PostMapping
    public ResponseEntity addPost(
            @RequestBody @Valid AddPostRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        List<String> authorization = headers.get("authorization");
        FindUserClientResult user = userClient.findUser(authorization.get(0), 1L);
        System.out.println("user.username() = " + user.username());
        long successId = postService.addPost(request.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(successId);
    }
}
