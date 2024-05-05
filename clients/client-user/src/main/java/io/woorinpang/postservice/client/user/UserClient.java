package io.woorinpang.postservice.client.user;

import io.woorinpang.postservice.client.user.model.FindUserClientResult;
import io.woorinpang.postservice.client.user.model.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClient {
    private final UserApi userApi;

    public FindUserClientResult findUser(String token, long userId) {
        JsonResponse<UserResponseDto> user = userApi.findUser(token, userId);
        return user.getData().toResult();
    }
}
