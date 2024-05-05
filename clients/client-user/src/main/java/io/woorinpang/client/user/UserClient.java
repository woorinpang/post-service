package io.woorinpang.client.user;

import io.woorinpang.client.user.model.FindUserClientResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClient {
    private final UserApi userApi;

    public FindUserClientResult findUser(long userId) {
        return userApi
                .findUser(userId)
                .toResult();
    }
}
