package io.woorinpang.postservice.core.api.controller.post;

import io.woorinpang.postservice.core.api.controller.post.request.AddPostRequest;
import io.woorinpang.postservice.core.api.support.response.ApiResponse;
import io.woorinpang.postservice.core.api.support.response.DefaultIdResponse;
import io.woorinpang.postservice.core.domain.post.PostService;
import io.woorinpang.postservice.core.domain.user.LoginUser;
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
    public ResponseEntity<ApiResponse<DefaultIdResponse>> addPost(
            @RequestBody @Valid AddPostRequest request
    ) {
        //TODO apigateway 에서 유저 정보를 responseBody 담아 보내야 함
        long userId = 1L;
        long successId = postService.addPost(new LoginUser(userId), request.toPostContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(new DefaultIdResponse(successId)));
    }

    //TODO 단건 조회

    //TODO 목록 조회 + 페이징

    //TODO 수정

    //TODO 삭제
}
