package io.woorinpang.postservice.client.user;

import io.woorinpang.postservice.client.user.model.FindUserClientResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserResponseDto {
    private long userId;
    private String username;
    private String email;
    private String name;
    private String userRole;
    private String userState;

    public FindUserClientResult toResult() {
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
