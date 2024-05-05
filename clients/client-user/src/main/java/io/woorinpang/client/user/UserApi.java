package io.woorinpang.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-api", url = "http://localhost:8000/user-service")
public interface UserApi {
    @GetMapping(value = "/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserResponse findUser(
            long userId
    );
}
