package io.woorinpang.postservice.core.domain.support.model;

import io.woorinpang.postservice.core.domain.user.User;

public record AuthenticatedUser(
        long id,
        String username,
        String email,
        String name
) {
    public User toUser() {
        return new User(id);
    }
}
