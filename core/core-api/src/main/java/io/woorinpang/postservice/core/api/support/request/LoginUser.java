package io.woorinpang.postservice.core.api.support.request;

import io.woorinpang.postservice.core.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoginUser {
    private long userId;
    private String username;
    private String email;
    private String name;
    private String role;

    public User toUser() {
        return new User(userId);
    }
}
