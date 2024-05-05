package io.woorinpang.client.user;

import io.woorinpang.client.user.model.FindUserClientResult;

public record UserResponse(
        long userId,
        String username,
        String email,
        String name,
        String userRole,
        String userState
) {
    FindUserClientResult toResult() {
        return FindUserClientResult.builder()
                .userId(userId)
                .username(username)
                .email(email)
                .name(name)
                .userRole(userRole)
                .userState(userState)
                .build();
    }

}
