package io.woorinpang.postservice.core.domain.support.model;

public record AuthenticatedUser(
        long id,
        String username,
        String email,
        String name
) {
}
