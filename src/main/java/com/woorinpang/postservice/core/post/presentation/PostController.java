package com.woorinpang.postservice.core.post.presentation;

import com.woorinpang.common.json.JsonResponse;
import com.woorinpang.postservice.core.post.application.PostService;
import com.woorinpang.postservice.core.post.presentation.dto.PostDtoMapper;
import com.woorinpang.postservice.core.post.presentation.dto.request.SavePostRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final PostDtoMapper postDtoMapper;

    @PostMapping
    public ResponseEntity<JsonResponse> savePost(@RequestBody @Valid SavePostRequest request) {
        //validate
        request.validate();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(JsonResponse.CREATED(postService.save(postDtoMapper.toCommand(request))));
    }
}
