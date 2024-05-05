package io.woorinpang.postservice.client.user;

import io.woorinpang.postservice.client.user.model.JsonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-api", url = "${user.api.url}")
public interface UserApi {

    @GetMapping(value = "/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    JsonResponse<UserResponseDto> findUser(
            @RequestHeader("Authorization") String token,
            @PathVariable("userId") long userId
    );
}
