package io.woorinpang.postservice.client.user.model;

import lombok.Builder;

@Builder
public record FindUserClientResult(
        long userId,
        String username,
        String email,
        String name,
        String userRole,
        String userState
) {

}
